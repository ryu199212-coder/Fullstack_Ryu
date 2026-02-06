import { Button } from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";

export default function EditDeleteButtons({ post, user, onEdit, dispatch, deletePostRequest }) {
  const isAuthor =
    user?.nickname?.trim().toLowerCase() ===
    post.authorNickname?.trim().toLowerCase();

  if (!isAuthor) return null;

  const buttonStyle = {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    gap: "8px", // 버튼 간격
    padding: "4px 8px",
    borderRadius: "6px",
    cursor: "pointer",
    transition: "background 0.2s, transform 0.1s",
  };

  return (
    <div style={{ display: "flex", gap: "12px", justifyContent: "center", marginTop: "10px" }}>
      <div
        onClick={() => onEdit(post)}
        style={{ ...buttonStyle, color: "#555" }}
        onMouseEnter={(e) => (e.currentTarget.style.background = "#f0f0f0")}
        onMouseLeave={(e) => (e.currentTarget.style.background = "transparent")}
      >
        <EditOutlined style={{ fontSize: "18px" }} />
        <span style={{ fontSize: "12px" }}>수정</span>
      </div>

      <div
        onClick={() => dispatch(deletePostRequest({ postId: post.id }))}
        style={{ ...buttonStyle, color: "red" }}
        onMouseEnter={(e) => (e.currentTarget.style.background = "#ffeaea")}
        onMouseLeave={(e) => (e.currentTarget.style.background = "transparent")}
      >
        <DeleteOutlined style={{ fontSize: "18px" }} />
        <span style={{ fontSize: "12px" }}>삭제</span>
      </div>
    </div>
  );
}
