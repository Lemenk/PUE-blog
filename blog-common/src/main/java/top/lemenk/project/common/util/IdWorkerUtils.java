package top.lemenk.project.common.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description TODO
 * @Author lemenk@163.com
 * @Created Date: 2021/1/26 15:23
 * @ClassName IdWorkerUtils
 * @Remark
 */
public class IdWorkerUtils {
    /** 开始时间截 (2015-01-01) */
    private final static long twepoch = 1489111610226L;

    /** 机器id所占的位数 */
    private final static long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    private final static long dataCenterIdBits = 5L;

    /** 序列在id中占的位数 */
    private final static long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private final static long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private final static long dataCenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 上次生成ID的时间截 */
    private static long lastTimestamp = -1L;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 工作机器ID(0~31) */
    private long workerId;

    /** 数据中心ID(0~31) */
    private long dataCenterId;

    private static long workerMask= -1L ^ (-1L << workerIdBits);
    //进程编码
    private static long processMask= ~(-1L << dataCenterIdBits);


    private static IdWorkerUtils idWorkerUtils;

    static {
        idWorkerUtils = new IdWorkerUtils(getMachineNum(),getProcessId());
    }

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 机器码
     * @param dataCenterId 数据中心ID
     */
    public IdWorkerUtils(long workerId, long dataCenterId) {
        this.workerId = workerId & workerMask;
        this.dataCenterId = dataCenterId & processMask;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    private synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取机器编码
     * @return
     */
    private static long getMachineNum(){
        long machinePiece;
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> e = null;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            sb.append(ni.toString());
        }
        machinePiece = sb.toString().hashCode();
        return machinePiece;
    }

    private static long getProcessId () {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long processId=Long.valueOf(runtimeMXBean.getName().split("@")[0]).longValue();
        return processId;
    }

    /**
     * 静态工具类
     *
     * @return
     */
    public static synchronized String generateId(){
        return String.valueOf(idWorkerUtils.nextId());
    }
}
