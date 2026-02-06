import React, { useEffect } from 'react';
import { Modal, Form, Input, Select, Row, Col, InputNumber, Upload } from 'antd';
import { PlusOutlined, EditOutlined } from '@ant-design/icons';

export default function EditPostModal({ 
    visible, 
    editPost, 
    loading, 
    onCancel, 
    onSubmit, 
    uploadFiles, 
    setUploadFiles 
}) {
    const [form] = Form.useForm();

    useEffect(() => {
        if (visible && editPost) {
            const hashtagArray = Array.isArray(editPost.hashtags) 
                ? editPost.hashtags 
                : (editPost.hashtags ? editPost.hashtags.split(',') : []);

            form.setFieldsValue({
                title: editPost.title,
                category: editPost.category,
                servingSize: editPost.servingSize,
                difficulty: editPost.difficulty,
                description: editPost.description || editPost.content,
                ingredients: editPost.ingredients,
                instructions: editPost.instructions || editPost.content,
                hashtags: hashtagArray,
            });
        }
    }, [visible, editPost, form]);

    // ✅ 미리보기 이미지 클릭 시 크게 보기 (선택 사항)
    const handlePreview = async (file) => {
        let src = file.url;
        if (!src && file.originFileObj) {
            src = await new Promise((resolve) => {
                const reader = new FileReader();
                reader.readAsDataURL(file.originFileObj);
                reader.onload = () => resolve(reader.result);
            });
        }
        const image = new Image();
        image.src = src;
        const imgWindow = window.open(src);
        imgWindow?.document.write(image.outerHTML);
    };

    return (
        <Modal
            title={<span><EditOutlined /> 레시피 수정하기</span>}
            open={visible}
            onCancel={() => {
                form.resetFields();
                onCancel();
            }}
            onOk={() => form.submit()}
            confirmLoading={loading}
            width={700}
            okText="수정완료"
            cancelText="취소"
            destroyOnClose
            forceRender
        >
            <Form
                form={form}
                layout="vertical"
                onFinish={onSubmit}
            >
                {/* ... (이전 코드와 동일한 Input 필드들) ... */}
                <Row gutter={16}>
                    <Col span={16}>
                        <Form.Item label="레시피 제목" name="title" rules={[{ required: true }]}><Input size="large" /></Form.Item>
                    </Col>
                    <Col span={8}>
                        <Form.Item label="카테고리" name="category"><Select size="large"><Select.Option value="한식">한식</Select.Option><Select.Option value="양식">양식</Select.Option></Select></Form.Item>
                    </Col>
                </Row>
                
                <Form.Item label="조리 순서" name="instructions"><Input.TextArea rows={4} /></Form.Item>

                {/* ✅ 핵심: 미리보기를 강제하는 Upload 설정 */}
                <Form.Item label="요리 이미지 변경">
                    <Upload 
                        listType="picture-card"
                        fileList={uploadFiles}
                        onChange={setUploadFiles}
                        onPreview={handlePreview}
                        beforeUpload={() => false} // 자동 업로드 방지
                        accept="image/*"
                    >
                        {uploadFiles.length >= 8 ? null : (
                            <div>
                                <PlusOutlined />
                                <div style={{ marginTop: 8 }}>사진 추가</div>
                            </div>
                        )}
                    </Upload>
                </Form.Item>
            </Form>
        </Modal>
    );
}