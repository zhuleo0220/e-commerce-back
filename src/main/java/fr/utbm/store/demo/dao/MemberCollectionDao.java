package fr.utbm.store.demo.dao;

import fr.utbm.store.demo.model.MemberCollection;
import fr.utbm.store.demo.model.MemberCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberCollectionDao {
    long countByExample(MemberCollectionExample example);

    int deleteByExample(MemberCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberCollection record);

    int insertSelective(MemberCollection record);

    List<MemberCollection> selectByExample(MemberCollectionExample example);

    MemberCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberCollection record, @Param("example") MemberCollectionExample example);

    int updateByExample(@Param("record") MemberCollection record, @Param("example") MemberCollectionExample example);

    int updateByPrimaryKeySelective(MemberCollection record);

    int updateByPrimaryKey(MemberCollection record);
}
