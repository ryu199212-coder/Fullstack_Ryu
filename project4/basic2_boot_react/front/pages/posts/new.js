import { Card, Form, Input, Button, Upload, message, Select, Row, Col, Typography, InputNumber } from "antd";
import { UploadOutlined, CoffeeOutlined } from "@ant-design/icons";
import { useDispatch, useSelector } from "react-redux";
import { useRouter } from "next/router";
import { createPostRequest, CREATE_POST_RESET } from "../../reducers/postReducer";
import { useState, useEffect } from "react";

const { Title } = Typography;

export default function NewPostPage() {
    const router = useRouter();
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    
    // 리덕스 상태 가져오기
    const { user } = useSelector((s) => s.auth);
    const { loading, error, createPostDone } = useSelector((s) => s.post);

    const [fileList, setFileList] = useState([]);
    
    // 1. 페이지 진입 시 초기화 및 권한 체크
    useEffect(() => {
        // ✅ 페이지에 들어오자마자 이전 등록 성공 기록을 초기화합니다.
        dispatch({ type: CREATE_POST_RESET });

        if (!user) {
            message.error("로그인이 필요합니다.");
            router.push("/login");
        }
    }, [user, router, dispatch]);

    // 2. 등록 완료 시 처리
    useEffect(() => {
        if (createPostDone) {
            message.success("레시피가 성공적으로 등록되었습니다!");
            // ✅ 다시 들어올 때를 대비해 상태를 먼저 리셋하고 이동합니다.
            dispatch({ type: CREATE_POST_RESET });
            router.push("/");
        }
    }, [createPostDone, router, dispatch]);

    // 3. 폼 제출 성공 시
    const onFinish = (values) => {
        const dto = {
            title: values.title,
            category: values.category,
            servingSize: values.servingSize,
            difficulty: values.difficulty,
            description: values.description,
            ingredients: values.ingredients,
            instructions: values.instructions,
            content: values.instructions, // 백엔드 content 필드 대응
            hashtags: values.hashtags ? values.hashtags.join(",") : "",
        };
        
        const files = fileList.map((f) => f.originFileObj);
        
        console.log("전송 시작 - DTO:", dto);
        // ✅ 여기서 router.push를 하면 안 됩니다. (성공 여부 확인 전이기 때문)
        dispatch(createPostRequest({ dto, files }));
    };

    // 4. 폼 제출 실패 시
    const onFinishFailed = (errorInfo) => {
        console.log('검증 실패:', errorInfo);
        message.error("입력되지 않은 필수 항목이 있습니다.");
    };

    return (
        <Card 
            title={<Title level={3} style={{ margin: 0 }}><CoffeeOutlined /> 새 레시피 등록</Title>} 
            style={{ maxWidth: 700, margin: "40px auto", borderRadius: "15px", boxShadow: "0 8px 24px rgba(0,0,0,0.1)" }}
        >
            <Form 
                form={form}
                onFinish={onFinish} 
                onFinishFailed={onFinishFailed} 
                layout="vertical" 
                requiredMark={true}
                initialValues={{
                    category: "한식",
                    servingSize: 1,
                    difficulty: "보통"
                }}
            >
                <Row gutter={16}>
                    <Col span={16}>
                        <Form.Item
                            label="레시피 제목"
                            name="title"
                            rules={[{ required: true, message: '제목을 입력해주세요!' }]}
                        >
                            <Input placeholder="예: 매콤 달콤 떡볶이" size="large" />
                        </Form.Item>
                    </Col>
                    <Col span={8}>
                        <Form.Item label="카테고리" name="category" rules={[{ required: true }]}>
                            <Select size="large">
                                <Select.Option value="한식">한식</Select.Option>
                                <Select.Option value="일식">일식</Select.Option>
                                <Select.Option value="중식">중식</Select.Option>
                                <Select.Option value="양식">양식</Select.Option>
                                <Select.Option value="디저트">디저트</Select.Option>
                            </Select>
                        </Form.Item>
                    </Col>
                </Row>

                <Row gutter={16}>
                    <Col span={12}>
                        <Form.Item 
                            label="인원수" 
                            name="servingSize" 
                            rules={[{ required: true, message: '인원수를 입력해주세요.' }]}
                        >
                            <InputNumber min={1} style={{ width: '100%' }} size="large" />
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <Form.Item label="난이도" name="difficulty" rules={[{ required: true }]}>
                            <Select size="large">
                                <Select.Option value="쉬움">쉬움</Select.Option>
                                <Select.Option value="보통">보통</Select.Option>
                                <Select.Option value="어려움">어려움</Select.Option>
                            </Select>
                        </Form.Item>
                    </Col>
                </Row>

                <Form.Item
                    label="레시피 간단 요약"
                    name="description"
                    rules={[{ required: true, message: '간단한 소개를 적어주세요.' }]}
                >
                    <Input placeholder="이 레시피의 특징을 한 줄로 소개해주세요." />
                </Form.Item>

                <Form.Item
                    label="재료 목록"
                    name="ingredients"
                    rules={[{ required: true, message: '필요한 재료를 입력해주세요.' }]}
                >
                    <Input.TextArea rows={3} placeholder="예: 떡볶이 떡 200g, 고추장 2큰술..." />
                </Form.Item>

                <Form.Item
                    label="조리 순서 (상세 설명)"
                    name="instructions"
                    rules={[{ required: true, message: '상세한 조리법을 적어주세요.' }]}
                >
                    <Input.TextArea rows={6} placeholder="1. 재료를 손질합니다..." />
                </Form.Item>

                <Form.Item label="해시태그" name="hashtags">
                    <Select mode="tags" style={{ width: "100%" }} placeholder="#태그 입력 후 Enter" />
                </Form.Item>

                <Form.Item label="요리 이미지 업로드 (최대 8장)">
                    <Upload 
                        multiple 
                        beforeUpload={() => false} 
                        fileList={fileList}
                        onChange={({ fileList }) => setFileList(fileList)}
                        listType="picture-card"
                        accept="image/*"
                    >
                        {fileList.length < 8 && (
                            <div>
                                <UploadOutlined />
                                <div style={{ marginTop: 8 }}>사진 추가</div>
                            </div>
                        )}
                    </Upload>
                </Form.Item>

                <Button 
                    type="primary" 
                    htmlType="submit" 
                    loading={loading} 
                    block 
                    size="large" 
                    style={{ height: "50px", fontSize: "18px", borderRadius: "8px", marginTop: "10px" }}
                >
                    레시피 공개하기
                </Button>
                
                {error && (
                    <p style={{ color: "red", marginTop: 15, textAlign: 'center' }}>
                        {typeof error === 'object' ? '등록 중 오류가 발생했습니다.' : error}
                    </p>
                )}
            </Form>
        </Card>
    );
}