package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipCommunicateFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.ll_send)
    LinearLayout llSend;
    @BindView(R.id.lv_msg)
    ListView lvMsg;
    List<Msg> msgList = new ArrayList<>();
    private ViewHolder holder;
    boolean sendReceive = true;
    private MyMsgAdapter arrayAdapter;


    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_communicate, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        ivBack.setVisibility(View.GONE);
        tvTitle.setText(getResources().getString(R.string.communication));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initData() {
        super.initData();
        Msg msg1 = new Msg("How are you ?", Msg.RECEIVED);
        msgList.add(msg1);

        Msg msg2 = new Msg("I'm fine,thank you, And you?", Msg.SENT);
        msgList.add(msg2);

        Msg msg3 = new Msg("I'm fine too.", Msg.RECEIVED);
        msgList.add(msg3);

        arrayAdapter = new MyMsgAdapter();
        lvMsg.setAdapter(arrayAdapter);

        lvMsg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(getResources().getString(R.string.is_delete))
                        .setPositiveButton(getResources().getString(R.string.delete), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                msgList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), getResources().getString(R.string.delete), Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton(getResources().getString(R.string.cancel), null).show();
                return false;
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_send:
                String sendContent = etContent.getText().toString();
                if (!sendContent.isEmpty()) {
                    if (sendReceive) {
                        Msg msg = new Msg(sendContent, Msg.SENT);
                        msgList.add(msg);
                        sendReceive = false;
                    } else {
                        Msg msg = new Msg(sendContent, Msg.RECEIVED);
                        msgList.add(msg);
                        sendReceive = true;
                    }

                    arrayAdapter.notifyDataSetChanged();
                    lvMsg.setSelection(msgList.size());//放置在第一行
                    etContent.setText("");
                }
                break;
        }
    }

    private class Msg {
        public static final int RECEIVED = 0;//收到一条消息  
        public static final int SENT = 1;//发出一条消息  
        private String content;//消息的内容  
        private int type;//消息的类型  

        public Msg(String content, int type) {
            this.content = content;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    private class MyMsgAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return msgList.size();
        }

        @Override
        public Object getItem(int i) {
            return msgList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = View.inflate(getActivity(), R.layout.item_msg, null);
                holder = new ViewHolder(view);
                view.setTag(holder);

            } else {
                holder = (ViewHolder) view.getTag();
            }

            Msg msg = msgList.get(i);
            if (msg.getType() == Msg.RECEIVED) {
                holder.llLeft.setVisibility(View.VISIBLE);
                holder.llRight.setVisibility(View.GONE);
                holder.tvReceiveMsg.setText(msg.getContent());
            } else {
                holder.llLeft.setVisibility(View.GONE);
                holder.llRight.setVisibility(View.VISIBLE);
                holder.tvSendMsg.setText(msg.getContent());
            }
            return view;
        }
    }

    static class ViewHolder {
        @BindView(R.id.iv_receive_user_photo)
        ImageView ivReceiveUserPhoto;
        @BindView(R.id.tv_receive_msg)
        TextView tvReceiveMsg;
        @BindView(R.id.ll_left)
        LinearLayout llLeft;
        @BindView(R.id.tv_send_msg)
        TextView tvSendMsg;
        @BindView(R.id.iv_send_user_photo)
        ImageView ivSendUserPhoto;
        @BindView(R.id.ll_right)
        LinearLayout llRight;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
