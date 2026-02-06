import cookie from "cookie";
import { message } from "antd"; 
import React, { useEffect, useState, useMemo } from "react"; 
import { useDispatch, useSelector } from "react-redux"; 
import { Card, Avatar, Spin, Descriptions, Form, Input, Button, Upload, List, Tabs } from "antd"; 
import { UploadOutlined } from "@ant-design/icons";  
import { useRouter } from "next/router";
import { updateNicknameRequest, updateProfileImageRequest, loginSuccess, logout } from "../reducers/authReducer";  
import { loadFollowersRequest, loadFollowingsRequest, unfollowRequest } from "../reducers/followReducer";
import api from "../api/axios";
import { wrapper } from "../store/configureStore";

export default function Mypage(){
    const dispatch = useDispatch();  
    const router   = useRouter();  
    const { user, loading } = useSelector((state) => state.auth);
    const [timestamp, setTimestamp] = useState(Date.now());
    const [fileList , setFileList] = useState([]);

    useEffect(() => {
        const verify = async () => {
            try {
                const token = localStorage.getItem("accessToken");
                if (!token) return;  
                const me = await api.get("/auth/me", {
                    headers: { Authorization: `Bearer ${token}` }, 
                    withCredentials: true, 
                });
                if (me?.data && me.data.nickname) {
                    dispatch(loginSuccess({ user: me.data }));  
                }
            } catch (error) {  
                dispatch(logout());
                router.replace("/login");
            } 
        }; 
        verify();
    } , [dispatch , router]);

    const { followersList = [], followingsList = [] } = useSelector((state) => state.follow);
    
    useEffect(() => {
        if(user?.id){
            dispatch(loadFollowersRequest());
            dispatch(loadFollowingsRequest());
        } 
    }, [user?.id, dispatch]);

    // ✅ 이미지 캐시 방지 쿼리스트링 적용
    const imageUrl = useMemo(() => {
        if (user?.ufile) {
            return `${process.env.NEXT_PUBLIC_API_BASE_URL}/${user.ufile}?t=${timestamp}`;
        }
        return undefined;
    }, [user?.ufile, timestamp]);

    if(loading) return <div style={{textAlign:'center', padding:'50px'}}><Spin size="large"/></div>;
    if(!user) return <p>로그인이 필요합니다.</p>;

    const tabItems = [
        {
            key: 'profile',
            label: '내 정보',
            children: (
                <>
                    <div style={{ display: "flex", alignItems: "center", marginBottom: 20 }}>
                        {/* ✅ key 부여하여 경로 변경 시 강제 리렌더링 */}
                        <Avatar src={imageUrl} size={64} key={user?.ufile}>
                            {user.nickname?.[0]}
                        </Avatar>
                        <Descriptions bordered column={1} size="middle" style={{ marginLeft: 20, flex: 1 }}> 
                            <Descriptions.Item label="이메일">{user.email}</Descriptions.Item>
                            <Descriptions.Item label="닉네임">{user.nickname}</Descriptions.Item>
                        </Descriptions>
                    </div> 

                    <Form onFinish={(v) => dispatch(updateNicknameRequest({ userId: user.id, nickname: v.nickname }))} layout="inline" style={{ marginBottom: 20 }}>
                        <Form.Item name="nickname" rules={[{ required: true, message: "닉네임을 입력하세요" }]}>
                            <Input placeholder="새 닉네임" />
                        </Form.Item>
                        <Button type="primary" htmlType="submit">닉네임 변경</Button>
                    </Form>

                    <Form layout="inline" style={{ marginBottom: 20 }}>
                        <Form.Item>
                            <Upload beforeUpload={() => false} fileList={fileList} onChange={({ fileList }) => setFileList(fileList)} maxCount={1}>
                                <Button icon={<UploadOutlined />}>이미지 선택</Button>
                            </Upload>
                        </Form.Item>
                        <Button
                            type="primary" 
                            onClick={() => {
                                if (fileList.length === 0) return message.warning("이미지를 선택하세요.");
                                const file = fileList[0]?.originFileObj;
                                dispatch(updateProfileImageRequest({ userId: user.id, file }));
                                setTimeout(() => setTimestamp(Date.now()), 1000); // 1초 뒤 강제 갱신
                                setFileList([]); 
                            }}
                        >
                            프로필 이미지 변경
                        </Button>
                    </Form> 
                </>
            )
        }
    ];

    return (
        <Card title="마이페이지" style={{ maxWidth: 800, margin: "20px auto" }}>
            <Tabs defaultActiveKey="profile" items={tabItems} />
        </Card>
    );
}

export const getServerSideProps = wrapper.getServerSideProps((store) => async (ctx) => {
    try {
        const me = await api.get("/auth/me", {
            headers: { cookie: ctx.req.headers.cookie || "" }, 
            withCredentials: true, 
        });
        if (me?.data && me.data.nickname) { 
            store.dispatch(loginSuccess({ user: me.data }));  
            return { props: {} }; 
        }
    } catch (error) {  
        return { redirect: { destination: "/login", permanent: false } };
    } 
    return { props: {} };
});