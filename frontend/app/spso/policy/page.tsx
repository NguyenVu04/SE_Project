'use client';
import React from "react";
import TopBar from "../../../components/ui/topbar";
import { Alert } from '@/components/ui/alert'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
export default function ChangePolicy() {
    const [pagenum, setPageNum] = React.useState('');    
    const [date, setDate] = React.useState('');  
    const [topics, setTopics] = React.useState<string[]>([]);  
    const [currentTopic, setCurrentTopic] = React.useState('');  
    const [error, setError] = React.useState('');

    const handleAddTopic = () => {
        if (currentTopic.trim() && !topics.includes(currentTopic)) {
          setTopics([...topics, currentTopic]);
          setCurrentTopic('');
        }
    };
    
    const handleRemoveTopic = (topicToRemove: string) => {
        setTopics(topics.filter((topic) => topic !== topicToRemove));
    };

    const onSubmit = () => {

    }
    
      return (
        <div className="flex flex-col min-h-screen">
            <TopBar></TopBar>
            {/* main content */}
            <div
                style={{ background: 'linear-gradient(to bottom, #0381FF, #02067A)' }}
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-black"
            >
            {/* manage printer */}
                <div className="bg-white p-6 rounded-lg shadow-lg max-w-5xl w-full mt-3 mb-3 justify-center items-center">
                    <h1 className="text-center text-4xl">THAY ĐỔI CHÍNH SÁCH</h1>
                    <hr className="h-1 mx-auto bg-black border-0 rounded md:my-5 dark:bg-gray-700 mb-5"/>
                    <form onSubmit={onSubmit} className="space-y-12">
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="pagenum">Số trang mặc định</Label>
                            <Input
                                className="w-full"
                                required
                                value={pagenum}
                                onChange={(e) => setPageNum(e.target.value)}
                                id="pagenum"
                                type="number"
                            />
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="date">Ngày cung cấp hàng tháng</Label>
                            <Input
                                className="w-full"
                                required
                                value={date}
                                onChange={(e) => setDate(e.target.value)}
                                id="date"
                                type="number"
                            />
                        </div>
                        <div className="flex w-full items-center gap-1.5">
                            <Label htmlFor="date" className="w-auto">Loại tập tin</Label>
                            <Input
                                className="w-3/4"
                                required
                                value={currentTopic}
                                onChange={(e) => setCurrentTopic(e.target.value)}
                                id="topics"
                                type="text"
                            />
                            <button
                                type="button"
                                onClick={handleAddTopic}
                                className=' w-1/4 rounded-lg text-white px-8 py-12 ml-10 cursor-pointer bg-hcmut-light hover:bg-hcmutLightBlue'
                                style={{ padding: '8px 12px', marginLeft: '10px', cursor: 'pointer' }}
                                >
                                Thêm loại
                            </button>
                        </div>
                        <div className="mt-5">
                                {topics.map((topic, index) => (
                                <span
                                    key={index}
                                    style={{
                                    display: 'inline-block',
                                    background: '#ddd',
                                    padding: '5px 10px',
                                    margin: '5px',
                                    borderRadius: '5px',
                                    cursor: 'pointer',
                                    }}
                                    onClick={() => handleRemoveTopic(topic)}
                                >
                                    {topic} &times;
                                </span>
                                ))}
                            </div>
                        {error && <Alert>{error}</Alert>}
                        <div className="w-full">
                            <Button className="w-full bg-hcmut-light" size="lg">
                                Lưu
                            </Button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
      );
}