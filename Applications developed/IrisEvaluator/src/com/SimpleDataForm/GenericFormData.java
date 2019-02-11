package com.SimpleDataForm;

public interface GenericFormData {
    public String[] getFields();
    public void setData(String[] fields);
    public String[] getData();
    public String toString();
    public String process();
    public String getResult();
}