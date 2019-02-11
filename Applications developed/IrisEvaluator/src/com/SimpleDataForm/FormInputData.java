package com.SimpleDataForm;

public interface FormInputData {
    // public String toString():
    //  Format the way it'll be printed to the output list.
    //
    // public static String[] getFields():
    //  Used to generate the correct amount of input fields in the form,
    //  the string values are the field names.
    //
    // public String process();
    //  Do something with the data, the result of this action is meant to be
    //  viewed with the getResult() method.
    //
    // public String getResult();
    //  Returns a result string, this string will be print by default at the
    //  FormGUI's outputLabel. If you don't need this functionalily just return
    //  a empty string.
    public String[] getFields();
    public String toString();
    public String process();
    public String getResult();
    public FormInputData create(String[] newData);
}
