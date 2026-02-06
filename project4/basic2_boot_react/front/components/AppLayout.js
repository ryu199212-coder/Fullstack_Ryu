import { Layout, Menu, Input, Row, Col, Drawer, Button, Grid, Dropdown, Space } from "antd";
import { 
    MenuOutlined, 
    SearchOutlined, 
    SettingOutlined, 
    UserOutlined, 
    DashboardOutlined, 
    FileTextOutlined,
    LogoutOutlined
} from "@ant-design/icons";
import Link from "next/link";
import { useSelector, useDispatch } from "react-redux";
import axios from "../api/axios";
import { logout, loginSuccess } from "../reducers/authReducer";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";

const { Header, Content } = Layout;
const { useBreakpoint } = Grid;

function AppLayout({ children, initialUser }){
    const { user } = useSelector((state) => state.auth);
    const dispatch = useDispatch();
    const router = useRouter();
    const screens = useBreakpoint();

    const [drawerOpen, setDrawerOpen] = useState(false);
    const [searchValue, setSearchValue] = useState("");

    // SSR 데이터와 리덕스 동기화
    useEffect(() => {
        if (initialUser && !user && initialUser.nickname) {
            dispatch(loginSuccess({ user: initialUser }));
        }
    }, [initialUser, user, dispatch]);

    // 보호할 경로 목록
    const protectedRouter = ["/mypage", "/followers", "/followings", "/adminPage"];

    useEffect(() => {
        const hasToken = typeof window !== "undefined" && localStorage.getItem("accessToken");

        if (!user && !initialUser && protectedRouter.includes(router.pathname)) {
            if (hasToken) {
                axios.get("/auth/me")
                    .then((res) => {
                        if (res.data && res.data.nickname) {
                            dispatch(loginSuccess({ user: res.data }));
                        }
                    })
                    .catch(() => {
                        dispatch(logout());
                        router.replace("/login");
                    });
            } else {
                router.replace("/login");
            }
        }
    }, [user, initialUser, router.pathname]);

    const handleLogout = async () => {
        try {
            await axios.post("/auth/logout");
        } catch (err) {
            console.error("로그아웃 API 실패:", err);
        } finally {
            if (typeof window !== "undefined") {
                localStorage.removeItem("accessToken");
                localStorage.removeItem("user");
            }
            dispatch(logout());
            router.replace("/login");
        }
    };

    const onSearch = (value) => {
        if (value) {
            router.push(`/hashtags?tag=${encodeURIComponent(value)}`);
            setSearchValue("");
        }
    };

    // ✅ 관리자 전용 드롭다운 메뉴
    const adminMenu = {
        items: [
            {
                key: '1',
                label: <Link href="/adminPage">사용자 관리</Link>,
                icon: <UserOutlined />,
            },
            {
                key: '2',
                label: <span onClick={() => router.push('/')}>레시피 관리</span>,
                icon: <FileTextOutlined />,
            },
            {
                type: 'divider',
            },
            {
                key: '3',
                label: <span onClick={() => router.push('/')}>재료 관리</span>,
                icon: <DashboardOutlined />,
               
            },
        ],
    };

    // ✅ 메인 메뉴 아이템 구성
    const menuItems = [
        ...(user && user.nickname
            ? [
                { key: "new", label: <Link href="/posts/new">✏️ NEW POST</Link> },
                { key: "profile", label: <Link href="/mypage">👤 MYPAGE </Link> },
                // ROLE_ADMIN일 경우 드롭다운 형태로 표시
                ...((user.role === 'ROLE_ADMIN' || user.role === 'ADMIN') 
                    ? [{ 
                        key: "admin-dropdown", 
                        label: (
                            <Dropdown menu={adminMenu} placement="bottomRight">
                                <span style={{ color: '#faad14', fontWeight: 'bold' }}>
                                    <SettingOutlined /> 관리자 센터
                                </span>
                            </Dropdown>
                        ) 
                    }] 
                    : []
                ),
                {
                    key: "logout",
                    label: <span onClick={handleLogout} style={{ cursor: "pointer", color: '#ff4d4f' }}><LogoutOutlined /> Logout</span>,
                },
            ]
            : [
                { key: "login", label: <Link href="/login">🔒 Login</Link> },
                { key: "signup", label: <Link href="/signup">👤 Signup</Link> },
            ]
        ),
    ];

    return (
        <Layout>
            <Header style={{ padding: "0 24px", height: 64, display: "flex", alignItems: "center", background: '#001529' }}>
                <Row align="middle" style={{ width: "100%" }}>
                    {/* 1. 로고 영역 */}
                    <Col flex="none">
                        <Link href="/" passHref legacyBehavior>
                            <a style={{ color: "#fff", fontWeight: "bold", fontSize: "20px", marginRight: "40px", textDecoration: "none", letterSpacing: '-0.5px' }}>
                                오늘 뭐먹지?!
                            </a>
                        </Link>
                    </Col>

                    {/* 2. 중간 여백 (flex="auto"를 주어 메뉴를 오른쪽 끝으로 밀어냄) */}
                    <Col flex="auto"></Col>

                    {/* 3. 메뉴 영역 (데스크탑 전용) */}
                    <Col flex="none" xs={0} sm={0} md={16} lg={14}>
                        <Menu 
                            theme="dark" 
                            mode="horizontal" 
                            items={menuItems} 
                            overflowedIndicator={null} 
                            selectable={false}
                            style={{ 
                                justifyContent: 'flex-end', 
                                borderBottom: 'none',
                                minWidth: '500px'
                            }} 
                        />
                    </Col>

                    {/* 4. 모바일 메뉴 버튼 (모바일 전용) */}
                    <Col flex="none" xs={2} md={0}>
                        <Button 
                            type="text" 
                            icon={<MenuOutlined style={{ color: "white", fontSize: 20 }} />} 
                            onClick={() => setDrawerOpen(true)} 
                        />
                    </Col>
                </Row>
            </Header>

            {/* 검색바 영역 (헤더 아래 고정) */}
            {screens.md && (
                <div style={{ display: "flex", justifyContent: "center", alignItems: "center", padding: "16px", background: "#fff", borderBottom: "1px solid #f0f0f0" }}>
                    <Input
                        prefix={<SearchOutlined style={{ color: "#bfbfbf" }} />}
                        placeholder="어떤 레시피를 찾으시나요? (해시태그 검색)"
                        value={searchValue}
                        onChange={(e) => setSearchValue(e.target.value)}
                        onPressEnter={(e) => onSearch(e.target.value)}
                        style={{ maxWidth: 700, width: "100%", borderRadius: "8px", background: "#f5f5f5", border: 'none', padding: "8px 16px" }}
                    />
                </div>
            )}

            {/* 모바일 사이드 메뉴 (Drawer) */}
            <Drawer title="전체 메뉴" placement="right" onClose={() => setDrawerOpen(false)} open={drawerOpen}>
                <Input.Search
                    placeholder="해시태그 검색"
                    enterButton="검색"
                    value={searchValue}
                    onChange={(e) => setSearchValue(e.target.value)}
                    onSearch={(value) => { setDrawerOpen(false); onSearch(value); }}
                    style={{ marginBottom: 20 }}
                />
                <Menu mode="vertical" items={menuItems} onClick={() => setDrawerOpen(false)} />
            </Drawer>

            <Content style={{ padding: "0", minHeight: 'calc(100vh - 64px)', background: '#f0f2f5' }}>
                <div style={{ maxWidth: '1200px', margin: '0 auto', padding: '24px' }}>
                    {children}
                </div>
            </Content>
        </Layout>
    );
}

export default AppLayout;