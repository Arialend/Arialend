package com.android.notepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.android.notepad.R;
import com.android.notepad.bean.NotepadBean;
// 日记的适配器
public class NotepadAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater; // 这个对象用于加载item的布局文件
    private List<NotepadBean> list;        // list是列表中需要显示的日记对象的集合
    public NotepadAdapter(Context context, List<NotepadBean>list){
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    // 返回集合的长度
    public int getCount() {
        return list == null?0:list.size();
    }

    @Override
    // 返回集合对应下标的对象
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    // 返回对象在集合中的下标
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.notepad_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NotepadBean notepadBean = (NotepadBean)getItem(position);
        viewHolder.tvNotepadContent.setText(notepadBean.getNotepadContent());
        viewHolder.tvNotepadTime.setText(notepadBean.getNotepadTime());
        return convertView;
    }

    class ViewHolder{
        TextView tvNotepadContent;
        TextView tvNotepadTime;
        public ViewHolder(View view){
            tvNotepadContent = view.findViewById(R.id.item_content); // 记录的内容
            tvNotepadTime = view.findViewById(R.id.item_time);       // 记录的时间

        }
    }
}
