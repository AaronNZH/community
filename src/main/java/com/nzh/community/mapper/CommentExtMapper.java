package com.nzh.community.mapper;

import com.nzh.community.model.Comment;
import com.nzh.community.model.CommentExample;
import com.nzh.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentExtMapper {
    int incCommentCount(Comment record);
}