package com.luomo.dialogdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luomo.dialogdemo.adapter.ShareAdapter;
import com.luomo.dialogdemo.domain.ShareDomain;
import com.luomo.dialogdemo.widget.dialog.CustomDialog;
import com.luomo.dialogdemo.widget.dialog.ICustomDialog;
import com.luomo.dialogdemo.widget.dialog.adapter.CommonAdapter;
import com.luomo.dialogdemo.widget.dialog.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_one:
                deleteHint();
                break;
            case R.id.tv_two:
                stsList();
                break;
            case R.id.tv_three:
                umengShare();
                break;
            case R.id.tv_four:
                iosUi();
                break;
        }
    }

    private void deleteHint() {
        new CustomDialog(mContext, R.layout.dialog_textview_ui, new ICustomDialog() {
            @Override
            public void inflateViewAndData(final CustomDialog dialog) {
                TextView tvContent = dialog.f(R.id.tv_content);
                tvContent.setText("就显示删除删除吧!");
                dialog.f(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.f(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        }, 0.6f, -1f).show();
    }

    private void stsList() {
        new CustomDialog(mContext, R.layout.dialog_listview_ui, new ICustomDialog() {
            @Override
            public void inflateViewAndData(final CustomDialog dialog) {
                List<String> list = new ArrayList<String>();
                list.add("编辑");
                list.add("删除");
                list.add("取消");
                ListView lvContent = dialog.f(R.id.lv_content);
                lvContent.setAdapter(new CommonAdapter<String>(mContext, list, R.layout.dialog_listview_ui_item) {


                    @Override
                    public void convert(final ViewHolder helper, String item) {
                        helper.setText(R.id.tv_content, item);
                        helper.getView(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                switch (helper.getPosition()) {
                                    case 0://编辑
                                        Toast.makeText(mContext,"编辑",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1://删除
                                        Toast.makeText(mContext,"删除",Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                    }
                });
            }
        }, 0.8f, -1f).show();
    }

    private void umengShare() {
        new CustomDialog(mContext, R.layout.dialog_share_ui, new ICustomDialog() {
            @Override
            public void inflateViewAndData(final CustomDialog dialog) {
                GridView gvContent = dialog.f(R.id.gv_content);
                final List<ShareDomain> mShares = new ArrayList<ShareDomain>();
                mShares.add(new ShareDomain(R.mipmap.umeng_socialize_qq_on,"QQ好友"));
                mShares.add(new ShareDomain(R.mipmap.umeng_socialize_qzone_on,"QQ空间"));
                mShares.add(new ShareDomain(R.mipmap.umeng_socialize_wechat,"微信好友"));
                mShares.add(new ShareDomain(R.mipmap.umeng_socialize_wxcircle,"微信朋友圈"));

                ShareAdapter gvAdapter = new ShareAdapter(mContext, mShares);
                gvContent.setAdapter(gvAdapter);
                //点击取消
                dialog.f(R.id.tv_dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                gvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this, mShares.get(i).name, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        },1f,-1f, Gravity.BOTTOM).show();
    }

    private void iosUi() {
        new CustomDialog(mContext, R.layout.dialog_ios_ui, new ICustomDialog() {
            @Override
            public void inflateViewAndData(final CustomDialog dialog) {
                List<String> list = new ArrayList<String>();
                list.add("相机");
                list.add("图库");
                list.add("调用系统图片");
                ListView lvContent = dialog.f(R.id.lv_content);
                lvContent.setAdapter(new CommonAdapter<String>(mContext, list, R.layout.dialog_ios_ui_item) {

                    @Override
                    public void convert(final ViewHolder helper, String item) {
                        helper.setText(R.id.tv_content, item);
                        helper.getView(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                switch (helper.getPosition()) {
                                    case 0://编辑
                                        Toast.makeText(mContext,"相机 ",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1://删除
                                        Toast.makeText(mContext,"图库",Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                    }
                });
                dialog.f(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        }, 1f, -1f, Gravity.BOTTOM).show();
    }
}