package com.SimpleDataForm;

import java.util.ArrayList;


public class FormDataCollection {
	// get,add,remove,clear
	private final ArrayList<GenericFormData> list;

	public FormDataCollection(){
		this.list = new ArrayList<>();
	}

	public void add(GenericFormData newData){
		this.list.add(newData);
	}

	public GenericFormData get(int index){
		return this.list.get(index);
	}

	public GenericFormData remove(int index){
		return this.list.remove(index);
	}

	public void clear(){
		this.list.clear();
	}

	public int size(){
		return this.list.size();
	}
}