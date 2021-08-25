package top.lemenk.project.common.vo;

/**
 * @Description 通用下拉vo
 * @Author lemenk@163.com
 * @Created Date: 2020/9/24 20:19
 * @ClassName DropBoxVO
 * @Remark
 */
public class DropBoxPageVO {
    private String id;

    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "DropBoxPageVO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
