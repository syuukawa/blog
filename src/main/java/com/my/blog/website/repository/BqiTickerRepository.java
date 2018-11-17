package com.my.blog.website.repository;


import com.my.blog.website.entity.BqiTickerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by jiutian on 2018/11/14.
 */
@Repository("bqiTickerRepository")
public interface BqiTickerRepository extends JpaRepository<BqiTickerTable, String> {

//    /**
//     * 通过名字相等查询，参数为name
//     * 相当于JPQL: SELECT p FROM Person p WHERE p.name=?1
//     */
//    List<BqiTickerTable> findAll(String status);

//
//    /**
//     * 通过名字Like查询，参数为name
//     * 相当于JPQL：SELECT p FROM Person p WHERE p.name LIKE ?1
//     */
//    List<BqiTickerTable> findByNameLike(String name);
//
//    /**
//     * 通过名字和地址查询，参数为name和address
//     * 相当于JPQL: SELECT p FROM Person p WHERE p.name=?1 AND p.address=?2
//     */
//    List<BqiTickerTable> findByNameAndAddress(String name, String address);
//
//
//    /**
//     * 获得符合查询条件的前10条数据
//     */
//    List<BqiTickerTable> findFirst10ByName(String name);
//
//
//    /**
//     * 获得符合查询条件的前30条数据
//     */
//    List<BqiTickerTable> findTop30ByName(String name);
//
//
//    /**
//     * @Query注解的使用
//     */
//    @Query("select p from Person p where p.address=?1")
//    List<BqiTickerTable> findByAddress(String address);
//
//
//    /**
//     * 更新语句
//     * @param name
//     * @return 更新语言影响的行数
//     */
//    @Modifying
//    @Transactional
//    @Query("update BqiTickerTable p set p.name=?1")
//    int setName(String name);
//
//
//    //=========分页和排序============
//    // 调用personRepository.findByName("xx", new Sort(Direction.ASC, "age"));
//    List<BqiTickerTable> findByName(String name, Sort sort);
//
//    // 调用 personRepository.findByName("xx", new PageRequest(0, 10));
//    Page<BqiTickerTable> findByName(String name, Pageable pageable);
//

    BqiTickerTable findOneByName(String name);


}
