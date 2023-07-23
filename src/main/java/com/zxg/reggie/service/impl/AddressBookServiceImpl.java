package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.entity.AddressBook;
import com.zxg.reggie.mapper.AddressBookMaper;
import com.zxg.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMaper, AddressBook> implements AddressBookService {
}
