package com.zxg.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxg.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMaper extends BaseMapper<AddressBook> {
}
