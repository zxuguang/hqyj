package com.zxg.shixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.shixun.entity.AddressBook;
import com.zxg.shixun.mapper.AddressBookMaper;
import com.zxg.shixun.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMaper, AddressBook> implements AddressBookService {
}
