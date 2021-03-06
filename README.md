# DialogDemo
一直在优化工具类的代码这个是现在使用的最终版，功能还是比较强大的
##代码
CustomDialog .java
```java
/**
 * @author :renpan
 * @version :v1.0
 * @class :
 * @date :2014-04-12 15:47
 * @description:弹出框类
 */
public class CustomDialog extends Dialog {
    private Context context;

    private ICustomDialog inflateData;//自己初始化adapter
    private int layoutResID;//弹出框的布局，默认为dialog_message
    /**
     * dialog的位置 -1为默认位置
     */
    private int gravity;
    /**
     * dialog的宽高比因子0-1之间 默认为0.8f、0.6f
     */
    private float widthScale, heightScale;

    public CustomDialog(Context context, int layoutId, ICustomDialog inflateData) {
        this(context, layoutId, inflateData, 0.8f, 0.6f);
    }

    public CustomDialog(Context context, int layoutId, ICustomDialog inflateData, float widthScale, float heightScale) {
        this(context, layoutId, inflateData, widthScale, heightScale,-1);
    }

    /**
     * @param context
     * @param layoutId
     * @param inflateData
     * @param widthScale -1表示使用布局文件的自身宽度
     * @param heightScale -1表示使用布局文件的自身高度
     * @param gravity dialog所在的位置 如Gravity.Bottom
     */
    public CustomDialog(Context context, int layoutId, ICustomDialog inflateData, float widthScale, float heightScale, int gravity) {
        super(context, R.style.Dialog);
        this.context = context;
        this.inflateData = inflateData;
        this.widthScale = widthScale;
        this.heightScale = heightScale;
        this.layoutResID = layoutId;
        this.gravity = gravity;
        setContentView(layoutResID);
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initData() {
        setDialogSize(widthScale, heightScale);
        if (null != inflateData) {
            inflateData.inflateViewAndData(this);
        }
    }

    /**
     * 获取某一类型对象
     *
     * @param viewId
     * @return
     */
    public <T extends View> T f(int viewId) {
        return (T) findViewById(viewId);
    }

    /**
     * 设置dialog的宽高
     */
    private void setDialogSize(float widthScale, float heightScale) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        if (-1.0f != widthScale) {
            lp.width = (int) (d.widthPixels * widthScale); // 默认宽度设置为屏幕的0.8
        }
        if (-1.0f != heightScale) {
            lp.height = (int) (d.heightPixels * heightScale); // 高度设置为屏幕的0.6
        }
        if(-1 != gravity) {
            lp.gravity = gravity;
        }
        dialogWindow.setAttributes(lp);
    }
}
```
ICustomDialog .java
```java
/**
 * Created by renpan on 2015-04-12.
 * 辅助弹出框完成弹出界面的赋值问题
 */
public interface ICustomDialog {
    /**
     * 弹出框，处理弹出界面的赋值问题
     * @param dialog promptDialog在此处类似于view
     */
    public void inflateViewAndData(CustomDialog dialog);
}
```
##实现效果
![这里写图片描述](http://img.blog.csdn.net/20160909093844571)
##下载地址
[https://github.com/407365100/DialogDemo](https://github.com/407365100/DialogDemo)
