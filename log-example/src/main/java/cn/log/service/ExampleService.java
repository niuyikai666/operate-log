package cn.log.service;

import cn.log.entity.Example;
import org.springframework.stereotype.Service;

/**
 * @author liwei
 * @title: ExampleService
 * @projectName wwmxd-log
 * @description: TODO
 * @date 2019-12-12 15:02
 */
@Service
public class ExampleService  implements IService<Example,String> {

    @Override
    public Example selectById(String id) {
        Example example=new Example();
        example.setId("1");
        //模拟更新 每次数据不一样
        example.setName("牛艺凯");
        return example;
    }
}
