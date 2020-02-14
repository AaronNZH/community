package com.nzh.community.mapper;

import com.nzh.community.dto.QuestionQueryDTO;
import com.nzh.community.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionExtMapper {
    void incView(Question record);
    void incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}