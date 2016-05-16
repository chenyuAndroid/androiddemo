package chenyu.com.cardviewdemo;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MessageObj {
    private String username;
    private String count;
    private String title;
    private String content;
    private int icon;

    public MessageObj(String username,int icon ,String count, String title, String content) {
        this.username = username;
        this.count = count;
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getCount() {
        return count;
    }

    public int getIcon() {
        return icon;
    }
}
