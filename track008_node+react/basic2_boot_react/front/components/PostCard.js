import { Card, List, Carousel, Image, Row, Col, message } from "antd";
import { CommentOutlined } from "@ant-design/icons"; // RetweetOutlined는 이제 RetweetButton에서 처리
import Link from "next/link";

import LikeButton from "./LikeButton";
import FollowButton from "./FollowButton";
import EditDeleteButtons from "./EditDeleteButtons";
import CommentSection from "./CommentSection";
import RetweetButton from "./RetweetButton"; // RetweetButton import

import { deletePostRequest } from "../reducers/postReducer";
import { addRetweetRequest, removeRetweetRequest } from "../reducers/retweetReducer";

export default function PostCard({
    post ,
    user ,
    likes ,
    likesCount ,
    retweetedPosts ,
    retweetsCount ,
    expandedPostId ,
    setExpandedPostId ,
    handleToggleLike ,
    handleToggleFollow ,
    handleEdit ,
    dispatch ,
    likeLoading ,
    followingsMap ,  
    followLoading ,
}){ 
    // 해시태그 배열 변환
    const hashtagList = Array.isArray(post?.hashtags) 
            ? post.hashtags 
            : post?.hashtags ? [post?.hashtags] : [];

    const isFollowing = followingsMap?.[String(post?.authorId)] === true;

    // view
    return <Card style={{ marginBottom: "30px" , borderRadius:"12px"}}>
        <List.Item>
            {/* 내가 리트윗한 글 표시 */}
            {retweetedPosts?.[String(post?.id)] && (
            <div style={{ fontSize: "12px", color: "green", marginBottom: "5px" }}>
                내가 리트윗한 글
            </div>
            )}

            {/* 이미지가 있을 경우 Carousel로 표시 */}
            {post?.imageUrls?.length > 0 && (
                <Carousel dots draggable style={{ marginBottom: "15px" }}>
                    {post.imageUrls.map((img, idx) => (
                    <div key={idx} style={{ textAlign: "center" }}>
                        <Image
                        src={`${process.env.NEXT_PUBLIC_API_BASE_URL}/${img}`}
                        alt={`post image ${idx}`}
                        style={{ maxWidth: "100%", borderRadius: "12px", objectFit: "cover" }}
                        />
                    </div>
                    ))}
                </Carousel>
            )} 

            {/* 글 내용, 작성일, 해시태그, 작성자 */}
            <List.Item.Meta description={
            <>
              <div style={{ whiteSpace: "pre-line", marginBottom: "8px", fontWeight: "bold" }}>
                {post?.content}
              </div>
              <div>
                작성일:{" "}
                {post?.createdAt
                  ? new Date(post.createdAt).toLocaleString()
                  : "작성일 정보 없음"}
              </div>

              {hashtagList.length > 0 && (
                <div>
                  해시태그:{" "}
                  {hashtagList.map((tag, idx) => (
                    <Link key={`${tag}-${idx}`} href={`/hashtags?tag=${encodeURIComponent(tag)}`}>
                      <span style={{ color: "#1890ff", marginRight: "8px", cursor: "pointer" }}>
                        #{tag}
                      </span>
                    </Link>
                  ))}
                </div>
              )}

              <div>작성자: {post?.authorNickname ?? "알 수 없음"}</div>
            </>
          }/>

         {/*  버튼영역 : 가로한줄로 배치   */} 
         <Row justify="space-between" align="middle" style={{marginTop: "10px"}}>
            {/* 좋아요 버튼 */}
            <Col>
              <LikeButton 
                postId={post?.id}
                user={user}
                liked={likes?.[String(post?.id)] === true}
                likes={likesCount?.[String(post?.id)] ?? post.likesCount ?? 0}
                onToggleLike={handleToggleLike}
                loading={likeLoading}
              />
            </Col>
            
            {/* 리트윗 버튼 (RetweetButton 사용) */}
            <Col>
            <RetweetButton
                user={user}
                postId={post?.id}
                // 안전하게 boolean 처리
                isRetweeted={!!retweetedPosts?.[String(post?.id)]}
                retweetCount={retweetsCount?.[String(post?.id)] ?? post?.retweetCount ?? 0}
                loading={false} // 필요 시 redux 로딩 상태로 연결
                onToggleRetweet={(postId, isRetweeted) => {
                if (!user) {
                    message.warning("로그인 후 이용 가능합니다.");
                    window.location.href = "/login";
                    return;
                }

                if (isRetweeted) {
                    dispatch(removeRetweetRequest({ postId }));
                } else {
                    dispatch(addRetweetRequest({ postId }));
                }
                }}
            />
            </Col>

            {/* 댓글 버튼 */}
            <Col>
              <div
                onClick={() => {
                  if (!user) {
                    message.warning("로그인 후 댓글 작성 가능합니다.");
                    window.location.href = "/login";
                    return;
                  }
                  setExpandedPostId(expandedPostId === post?.id ? null : post?.id);
                }}
                style={{ cursor: "pointer" }}
              >
                <CommentOutlined style={{ fontSize: "20px", color: "#555" }} />
                <div style={{ fontSize: "12px" }}>댓글</div>
              </div>
            </Col>

            {/* 팔로우 버튼 */}
            <Col>
              <FollowButton
                authorId={post?.authorId ?? null}
                user={user}
                isFollowing={isFollowing}
                onToggleFollow={handleToggleFollow}
                loading={followLoading}
              />
            </Col>

            {/* 수정/삭제 버튼 */}
            <Col>
              <EditDeleteButtons
                post={post}
                user={user}
                onEdit={handleEdit}
                dispatch={dispatch}
                deletePostRequest={deletePostRequest}
              />
            </Col>
         </Row> 

        {/* 댓글 섹션 */}
        {expandedPostId === post?.id && <CommentSection postId={post?.id} user={user} />}
    </List.Item>
</Card>; 
};
