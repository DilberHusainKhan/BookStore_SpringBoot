package com.humayun.bookStore.service;


import com.humayun.bookStore.entity.MyBook;
import com.humayun.bookStore.repository.MyBookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBookService {

    @Autowired
    private MyBookRepository mBRepo;

    public void saveMyBook(MyBook b){
        mBRepo.save(b);
    }
    public List<MyBook> getAllBook(){
        return mBRepo.findAll();
    }

    public void deleteById(int id){
        mBRepo.deleteById(id);
    }

}
