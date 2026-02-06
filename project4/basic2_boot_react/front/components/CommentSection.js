import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Input, Button, List } from "antd";

import {
  fetchCommentsRequest,
  createCommentRequest,
  updateCommentRequest,
  deleteCommentRequest,
} from "../reducers/commentReducer";

export default function CommentSection({ postId, user }) {
  const dispatch = useDispatch();

  const comments = useSelector((state) => state.comment.comments[postId] || []);
  const loading = useSelector((state) => state.comment.loading);

  const [newContent, setNewContent] = useState("");  
  const [editId, setEditId] = useState(null);       
  const [editContent, setEditContent] = useState(""); 

 
  useEffect(() => {
    dispatch(fetchCommentsRequest({ postId }));  
  }, [dispatch, postId]);

 
  const handleCreate = () => {
    if (!newContent.trim()) return;
    dispatch(createCommentRequest({ postId, dto: { content: newContent } }));  
    setNewContent("");
  };

 
  const handleUpdate = (commentId) => {
    if (!editContent.trim()) return;
    dispatch(updateCommentRequest({ postId, commentId, dto: { content: editContent } }));  
    setEditId(null);
    setEditContent("");
  };
 
  const handleDelete = (commentId) => {
    dispatch(deleteCommentRequest({ postId, commentId }));  
  };

  return (
    <div
      style={{
        marginTop: "15px",
        padding: "10px",
        backgroundColor: "#fafafa",
        borderRadius: "6px",
      }}
    >
      <strong>댓글</strong>
 
      {user && (
        <div style={{ marginTop: "10px" }}>
          <Input.TextArea
            rows={2}
            value={newContent}
            onChange={(e) => setNewContent(e.target.value)}
            placeholder="댓글을 입력하세요"
          />
          <Button type="primary" onClick={handleCreate} style={{ marginTop: "5px" }}>
            등록
          </Button>
        </div>
      )}

 
      <List
        style={{ marginTop: "10px" }}
        loading={loading}
        dataSource={comments}
        renderItem={(c) => (
          <List.Item
            actions={
              user && user.nickname === c.authorNickname
                ? [
                    editId === c.id ? (
                      <Button onClick={() => handleUpdate(c.id)}>저장</Button>
                    ) : (
                      <Button
                        onClick={() => {
                          setEditId(c.id);
                          setEditContent(c.content);
                        }}
                      >
                        수정
                      </Button>
                    ),
                    <Button danger onClick={() => handleDelete(c.id)}>삭제</Button>,
                  ]
                : []
            }
          >
            {editId === c.id ? (
              <Input.TextArea
                rows={2}
                value={editContent}
                onChange={(e) => setEditContent(e.target.value)}
              />
            ) : (
              <div>
                <strong>{c.authorNickname}</strong>: {c.content}
                <div style={{ fontSize: "12px", color: "#888" }}>
                  {new Date(c.createdAt).toLocaleString()}
                </div>
              </div>
            )}
          </List.Item>
        )}
      />
    </div>
  );
}
