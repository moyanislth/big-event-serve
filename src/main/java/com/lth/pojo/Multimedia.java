package com.lth.pojo;

// 引入必要的库
import java.io.Serializable;

// 定义一个泛型类Multimedia，T代表媒体类型
public class Multimedia<T extends Serializable> {
    // 媒体内容
    private T content;

    // 媒体类型描述
    private String type;

    // 媒体标题
    private String title;

    // 媒体创建时间
    private long creationTime;

    // 媒体文件大小（以字节为单位）
    private long fileSize;

    // 构造函数
    public Multimedia(T content, String type, String title, long creationTime, long fileSize) {
        this.content = content;
        this.type = type;
        this.title = title;
        this.creationTime = creationTime;
        this.fileSize = fileSize;
    }

    // Getter和Setter方法
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    // 播放媒体内容的方法，具体实现依赖于媒体类型
    public void play() {
        if (content instanceof String) {
            // 处理文本内容
            System.out.println("Playing text: " + content);
        } else if (content instanceof byte[]) {
            // 处理图片或音频内容
            // 这里需要具体的播放逻辑
            System.out.println("Playing media content");
        } else {
            System.out.println("Unsupported media type");
        }
    }

    // 其他可能的方法，比如保存、分享等
    public void save() {
        // 保存媒体内容的逻辑
    }

    public void share() {
        // 分享媒体内容的逻辑
    }

    // 可以添加更多的方法来处理不同的媒体类型
}