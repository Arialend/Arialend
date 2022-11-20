package com.android.notepad.bean;
// 日记的实体类
public class NotepadBean {
    private String id;             // 记录的编号
    private String notepadContent; // 记录的内容
    private String notepadTime;    // 记录的时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotepadContent() {
        return notepadContent;
    }

    public void setNotepadContent(String notepadContent) {
        this.notepadContent = notepadContent;
    }

    public String getNotepadTime() {
        return notepadTime;
    }

    public void setNotepadTime(String notepadTime) {
        this.notepadTime = notepadTime;
    }
}
