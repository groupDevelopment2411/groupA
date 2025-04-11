package com.example.demo.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateService {

	@Autowired
	private UpdateMapper mapper;
	
//	public List<Update> findUserById(int id){
//		return mapper.findUserById(id);
//	}
	
    public List<Update> findUserById(int id) {
        return mapper.findUserById(id);
    }
    
//    public void updateinsert(Update update) {
//        mapper.updateinsert(update);
//    }
    
    
    public void updateinsert(Update update) {
        // デバッグ用ログを追加
        System.out.println("Service - Update ID: " + update.getId());
        System.out.println("Service - Name: " + update.getName());
        System.out.println("Service - Age: " + update.getAge());
        System.out.println("Service - Password: " + update.getPassword1());
        System.out.println("Service - Start Date: " + update.getStartDate());
        System.out.println("Service - End Date: " + update.getEndDate());
        
//      終了日が空白かヌルならヌルを入れる
        if (update.getEndDate() == null || update.getEndDate().isEmpty()) {
            update.setEndDate(null);
        }


        
        mapper.updateinsert(update);
    }
	
}
