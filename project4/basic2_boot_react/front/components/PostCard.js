import { Card, List, Carousel, Image, Row, Col, message, Tag, Typography, Space } from "antd";
import { CommentOutlined, ClockCircleOutlined, FireOutlined, UserOutlined } from "@ant-design/icons";
import Link from "next/link";

import LikeButton from "./LikeButton";
import FollowButton from "./FollowButton";
import EditDeleteButtons from "./EditDeleteButtons";
import CommentSection from "./CommentSection";
import RetweetButton from "./RetweetButton";

import { deletePostRequest } from "../reducers/postReducer";
import { addRetweetRequest, removeRetweetRequest } from "../reducers/retweetReducer";

const { Title, Text } = Typography;

export default function PostCard({
    post, user, likes, likesCount, retweetedPosts, retweetsCount,
    expandedPostId, setExpandedPostId, handleToggleLike, handleToggleFollow,
    handleEdit, dispatch, likeLoading, followingsMap, followLoading,
}) { 
    // 해시태그 처리
    const hashtagList = Array.isArray(post?.hashtags) ? post.hashtags : [];
    const isFollowing = followingsMap?.[String(post?.authorId)] === true;

    // ✅ 삭제 권한 체크: 작성자 본인이거나 관리자(1@1)일 때만 가능
    const canManage = user && (user.id === post?.authorId || user.email === '1@1');

    return (
        <Card style={{ marginBottom: "30px", borderRadius: "12px", overflow: "hidden" }} hoverable>
            <List.Item style={{ borderBottom: "none", padding: 0 }}>
                {/* 1. 상단 정보 */}
                <Row justify="space-between" align="middle" style={{ marginBottom: "10px" }}>
                    <Col>
                        {post?.category && <Tag color="orange">{post.category}</Tag>}
                        {retweetedPosts?.[String(post?.id)] && (
                            <Text type="success" style={{ fontSize: "12px" }}>
                                <FireOutlined /> 내가 리트윗한 레시피
                            </Text>
                        )}
                    </Col>
                    <Col>
                        <FollowButton
                            authorId={post?.authorId}
                            user={user}
                            isFollowing={isFollowing}
                            onToggleFollow={handleToggleFollow}
                            loading={followLoading}
                        />
                    </Col>
                </Row>

                {/* 2. 레시피 제목 (상세 페이지 링크: /post/[id] 로 경로 수정 확인 필요) */}
                <div style={{ marginBottom: "15px" }}>
                    {/* 경로가 /posts/ 인지 /post/ 인지 확인하세요. 보통 [id].js가 post 폴더 안에 있으면 /post/ 입니다. */}
                    <Link href={`/posts/${post?.id}`}>
                        <Title level={4} style={{ cursor: "pointer" }}>
                            {post?.title}
                        </Title>
                        </Link>
                    {/* <Link href={`/post/${post?.id}`}>
                        <Title 
                            level={4} 
                            style={{ marginBottom: "5px", cursor: "pointer", color: '#1890ff' }}
                        >
                            {post?.title || "제목 없는 레시피"}
                        </Title>
                    </Link> */}
                    <Space split={<Text type="secondary">|</Text>}>
                        <Text type="secondary"><UserOutlined /> {post?.servingSize}인분</Text>
                        <Text type="secondary"><ClockCircleOutlined /> {post?.difficulty}</Text>
                        <Text type="secondary">작성자: {post?.authorNickname || "익명"}</Text>
                    </Space>
                </div>

                {/* 3. 이미지 Carousel */}
                {post?.images?.length > 0 && (
                    <Carousel dots draggable style={{ marginBottom: "15px" }}>
                        {post.images.map((img, idx) => (
                            <div key={idx} style={{ textAlign: "center", backgroundColor: "#f0f0f0" }}>
                                <Link href={`/post/${post?.id}`}>
                                    <img
                                        src={img.src.startsWith('http') ? img.src : `${process.env.NEXT_PUBLIC_API_BASE_URL}/${img.src}`}
                                        alt={`recipe-step-${idx}`}
                                        style={{ 
                                            width: "100%",
                                            maxHeight: "400px", 
                                            borderRadius: "8px", 
                                            objectFit: "cover", 
                                            cursor: "pointer" 
                                        }}
                                    />
                                </Link>
                            </div>
                        ))}
                    </Carousel>
                )} 

                {/* 4. 본문 내용 및 해시태그 */}
                <List.Item.Meta 
                    description={
                        <>
                            <div style={{ whiteSpace: "pre-line", color: "#333", marginBottom: "10px" }}>
                                {post?.description || post?.content}
                            </div>
                            
                            {hashtagList.length > 0 && (
                                <div style={{ marginBottom: "10px" }}>
                                    {hashtagList.map((tag, idx) => (
                                        <Link key={`${tag}-${idx}`} href={`/search/hashtag?tag=${encodeURIComponent(tag)}`}>
                                            <span style={{ color: "#1890ff", marginRight: "8px", cursor: "pointer" }}>
                                                #{tag}
                                            </span>
                                        </Link>
                                    ))}
                                </div>
                            )}
                            <Text type="secondary" style={{ fontSize: "12px" }}>
                                {post?.createdAt ? new Date(post.createdAt).toLocaleString() : ""}
                            </Text>
                        </>
                    }
                />

                {/* 5. 액션 버튼 영역 */} 
                <Row justify="space-around" align="middle" style={{ marginTop: "20px", paddingTop: "10px", borderTop: "1px solid #f0f0f0" }}>
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
                    <Col>
                    <RetweetButton
                        user={user}
                        postId={post?.id}
                        isRetweeted={!!retweetedPosts?.[String(post?.id)]}
                        retweetCount={retweetsCount?.[String(post?.id)] ?? post?.retweetCount ?? 0}
                        onToggleRetweet={(postId, isRetweeted) => {
                            if (!user) return message.warning("로그인이 필요합니다.");
                            
                            if (isRetweeted) {
                                // 삭제는 URL 파라미터로 가니까 그대로 postId 사용
                                dispatch(removeRetweetRequest({ postId }));
                            } else {
                                // ✅ 핵심: Saga가 originalPostId를 찾을 수 있게 키 이름을 맞춰줍니다!
                                dispatch(addRetweetRequest({ originalPostId: postId }));
                            }
                        }}
                    />
                </Col>
                    <Col>
                        <Space direction="vertical" align="center" style={{ cursor: "pointer" }} onClick={() => {
                            if (!user) return message.warning("로그인이 필요합니다.");
                            setExpandedPostId(expandedPostId === post?.id ? null : post?.id);
                        }}>
                            <CommentOutlined style={{ fontSize: "20px", color: expandedPostId === post?.id ? "#1890ff" : "inherit" }} />
                            <Text style={{ fontSize: "12px" }}>댓글</Text>
                        </Space>
                    </Col>
                    
                    {/* 6. 수정/삭제 버튼: 권한이 있을 때만 렌더링하도록 EditDeleteButtons 내부 혹은 여기서 제어 */}
                    <Col>
                        {canManage && (
                            <EditDeleteButtons
                                post={post}
                                user={user}
                                onEdit={handleEdit}
                                dispatch={dispatch}
                                deletePostRequest={deletePostRequest}
                            />
                        )}
                    </Col>
                </Row> 

                {/* 7. 댓글 섹션 */}
                {expandedPostId === post?.id && (
                    <div style={{ marginTop: "20px" }}>
                        <CommentSection postId={post?.id} user={user} />
                    </div>
                )}
            </List.Item>
        </Card>
    );
};