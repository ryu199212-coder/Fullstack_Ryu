import { useState, useEffect, useMemo, useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Spin, message, Tabs, Button, Modal, Form, Input, Upload, Radio } from "antd"; 
import { PlusOutlined, UploadOutlined, FilterOutlined } from "@ant-design/icons";
import { useRouter } from 'next/router';
import InfiniteScroll from "react-infinite-scroll-component";

import MaterialList from "../components/MaterialList"; 
import PostList from "../components/PostList";
import EditPostModal from "../components/EditPostModal";

import { LOAD_MATERIALS_REQUEST, ADD_MATERIAL_REQUEST } from "../reducers/material";
import { 
    fetchPostsPagedRequest, 
    fetchCategoryPostsRequest, 
    fetchLikedPostsRequest, 
    fetchMyAndRetweetsRequest, 
    updatePostRequest,
    UPDATE_POST_RESET 
} from "../reducers/postReducer";
import { addLikeRequest, removeLikeRequest, fetchMyLikesRequest } from "../reducers/likeReducer";
import { toggleFollowRequest, loadFollowingsRequest } from "../reducers/followReducer";
import { fetchMyRetweetsRequest } from "../reducers/retweetReducer";

export default function Home() {
    const dispatch = useDispatch();
    const router = useRouter();
    const [form] = Form.useForm();

    const { user } = useSelector((state) => state.auth);
    const { posts, likedPosts, myAndRetweets, loading, hasNext, updatePostDone } = useSelector((state) => state.post);
    const { likes = {}, likesCount = {}, loading: likeLoading } = useSelector((state) => state.like);
    const { followingsMap, loading: followLoading } = useSelector((state) => state.follow);
    const { retweets, retweetsCount } = useSelector((state) => state.retweet);
    const { mainMaterials, loadMaterialsLoading } = useSelector((state) => state.material);

    const [mounted, setMounted] = useState(false); // Hydration 에러 방지용
    const [expandedPostId, setExpandedPostId] = useState(null);
    const [isEditModalVisible, setIsEditModalVisible] = useState(false);
    const [editPost, setEditPost] = useState(null);
    const [uploadFiles, setUploadFiles] = useState([]);
    const [pageAll, setPageAll] = useState(1);
    const [currentCategory, setCurrentCategory] = useState("전체"); 
    const [isMaterialModalVisible, setIsMaterialModalVisible] = useState(false);
    const [materialFile, setMaterialFile] = useState(null);

    // 마운트 체크
    useEffect(() => {
        setMounted(true);
    }, []);

    useEffect(() => {
        if (updatePostDone) {
            message.success("레시피가 수정되었습니다.");
            setIsEditModalVisible(false);
            setEditPost(null);
            setUploadFiles([]);
            dispatch({ type: UPDATE_POST_RESET });
        }
    }, [updatePostDone, dispatch]);

    const handleEdit = useCallback((post) => { 
        setEditPost(post); 
        setIsEditModalVisible(true); 
        setUploadFiles([]); 
    }, []);

    // ✅ 이미지 미리보기 생성 핸들러 (FileReader 방식)
    const handleFileChange = useCallback(({ fileList }) => {
        setUploadFiles(fileList);

        fileList.forEach((file) => {
            if (!file.url && !file.preview && file.originFileObj) {
                const reader = new FileReader();
                reader.onload = (e) => {
                    file.preview = e.target.result;
                    // 새로운 배열로 복사하여 리액트가 변화를 감지하게 함
                    setUploadFiles([...fileList]); 
                };
                reader.readAsDataURL(file.originFileObj);
            }
        });
    }, []);

    const handleEditSubmit = useCallback((values) => {
        if (!editPost) return;
        dispatch(updatePostRequest({
            postId: editPost.id,
            dto: {
                ...values,
                hashtags: Array.isArray(values.hashtags) ? values.hashtags.join(",") : values.hashtags,
            },
            files: uploadFiles.map(f => f.originFileObj).filter(Boolean), 
        }));
    }, [dispatch, editPost, uploadFiles]);

    const onSaveMaterial = useCallback((values) => {
        const formData = new FormData();
        formData.append("title", values.title);
        formData.append("category", values.category);
        if (materialFile) formData.append("file", materialFile);
        dispatch({ type: ADD_MATERIAL_REQUEST, data: formData });
        setIsMaterialModalVisible(false);
        form.resetFields();
    }, [dispatch, materialFile, form]);

    const handleToggleLike = useCallback((postId) => {
        if (!user) return message.warning("로그인 후 이용 가능합니다.");
        likes[String(postId)] === true ? dispatch(removeLikeRequest({ postId })) : dispatch(addLikeRequest({ postId }));
    }, [user, likes, dispatch]);

    const handleToggleFollow = useCallback((authorId) => {
        if (!user) return message.warning("로그인 후 이용 가능합니다.");
        dispatch(toggleFollowRequest(authorId));
    }, [user, dispatch]);

    const onCategoryChange = useCallback((e) => {
        const category = e.target.value;
        setCurrentCategory(category);
        setPageAll(1);
        if (category === "전체") {
            dispatch(fetchPostsPagedRequest({ page: 1, size: 10 }));
            setPageAll(2);
        } else {
            dispatch(fetchCategoryPostsRequest({ category }));
        }
    }, [dispatch]);

    useEffect(() => {
        dispatch(fetchPostsPagedRequest({ page: 1, size: 10 }));
        setPageAll(2);
        if (user) {
            dispatch({ type: LOAD_MATERIALS_REQUEST, data: 1 });
            dispatch(fetchLikedPostsRequest({ page: 1, size: 10 }));
            dispatch(fetchMyAndRetweetsRequest({ page: 1, size: 10 }));
            dispatch(fetchMyLikesRequest({ userId: user.id }));
            dispatch(loadFollowingsRequest());
        }
    }, [dispatch, user]);

    const fetchMoreAll = useCallback(() => {
        if (!hasNext || currentCategory !== "전체") return;
        dispatch(fetchPostsPagedRequest({ page: pageAll, size: 10 }));
        setPageAll((prev) => prev + 1);
    }, [hasNext, currentCategory, pageAll, dispatch]);

    const tabItems = useMemo(() => {
        const items = [{
            key: "all",
            label: "🍳 레시피 피드",
            children: (
                <div style={{ padding: '10px 0' }}>
                    <div style={{ textAlign: 'center', marginBottom: '20px' }}>
                        <Radio.Group value={currentCategory} onChange={onCategoryChange} buttonStyle="solid">
                            {["전체", "한식", "일식", "중식", "양식", "디저트"].map(cat => (
                                <Radio.Button key={cat} value={cat}>{cat}</Radio.Button>
                            ))}
                        </Radio.Group>
                    </div>
                    <InfiniteScroll
                        dataLength={posts.length}
                        next={fetchMoreAll}
                        hasMore={hasNext && currentCategory === "전체"}
                        loader={<Spin style={{ display: 'block', margin: '20px auto' }} />}
                    >
                        <PostList
                            posts={posts} user={user} likes={likes} likesCount={likesCount}
                            retweetedPosts={retweets} retweetsCount={retweetsCount}
                            expandedPostId={expandedPostId} setExpandedPostId={setExpandedPostId}
                            handleToggleLike={handleToggleLike} handleToggleFollow={handleToggleFollow}
                            handleEdit={handleEdit} dispatch={dispatch}
                            followingsMap={followingsMap}
                        />
                    </InfiniteScroll>
                </div>
            )
        }];

        if (user) {
            items.push(
                { key: "material", label: "🍲 추천 식재료", children: <div style={{ padding: '20px' }}><MaterialList materials={mainMaterials || []} /></div> },
                { key: "liked", label: "❤️ 좋아요", children: <PostList posts={likedPosts} user={user} likes={likes} followingsMap={followingsMap} handleToggleLike={handleToggleLike} handleToggleFollow={handleToggleFollow} dispatch={dispatch} title="내가 찜한 레시피" /> },
                { key: "myActivity", label: "📌 내 활동", children: <PostList posts={myAndRetweets} user={user} likes={likes} followingsMap={followingsMap} handleToggleLike={handleToggleLike} handleToggleFollow={handleToggleFollow} dispatch={dispatch} title="내 활동 기록" /> }
            );
        }
        return items;
    }, [user, currentCategory, posts, hasNext, likedPosts, myAndRetweets, handleToggleLike, handleToggleFollow, handleEdit, likes, followingsMap]);

    return (
        <div style={{ background: '#f5f5f5', minHeight: '100vh' }}>
            <Tabs defaultActiveKey="all" centered items={tabItems} style={{ padding: '0 20px' }} />

            {/* Hydration 오류 방지: 마운트 후에만 모달 렌더링 */}
            {mounted && (
                <EditPostModal
                    visible={isEditModalVisible} editPost={editPost} loading={loading}
                    onCancel={() => { setIsEditModalVisible(false); setEditPost(null); }} 
                    onSubmit={handleEditSubmit} 
                    uploadFiles={uploadFiles} 
                    setUploadFiles={handleFileChange} 
                />
            )}
        </div>
    );
}