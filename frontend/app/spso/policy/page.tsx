'use client';
import React, { useEffect, useState } from "react";
import TopBar from "../../../components/ui/topbar";
import { Alert } from '@/components/ui/alert';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import getConfig from "@/lib/get-config";
import addConfig from "@/lib/add-config";
import { redirect } from "next/navigation";
import getUserId from "@/lib/user-id";
import Image from "next/image";

export type Config = {
    defaultNumberOfPages: number,
    paperSupplyDay: number,
    createdBy: string,
    fileTypes: string[]
}
export default function ChangePolicy() {
    const [id, setId] = useState<string | null>(null);
    useEffect(() => {
        getUserId("spso")
            .then((id) => {
                setId(id);
            })
            .catch(() => {
                redirect("/");
            })
    }, []);

    const [pagenum, setPageNum] = useState<number>(0);
    const [day, setDay] = useState<number>(0);
    const [topics, setTopics] = useState<string[]>([]);
    const [currentTopic, setCurrentTopic] = useState<string>('');
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getConfig()
            .then((config) => {
                setPageNum(config.defaultNumberOfPages);
                setDay(config.paperSupplyDay);
                setTopics(config.fileTypes);
            })
            .catch((error) => {
                setError(error.message);
            });
    }, []);

    const handleAddTopic = () => {
        if (currentTopic.trim() && !topics.includes(currentTopic)) {
            setTopics([...topics, currentTopic]);
            setCurrentTopic('');
        }
    };

    const handleRemoveTopic = (topicToRemove: string) => {
        setTopics(topics.filter((topic) => topic !== topicToRemove));
    };

    if (!id) {
        return (
            <div className="flex flex-col min-h-screen justify-center items-center">
                <Image src={"/hcmut.svg"} alt="logo" width={64} height={64} />
            </div>
        )
    }

    return (
        <div className="flex flex-col min-h-screen">
            <TopBar></TopBar>
            {/* main content */}
            <div
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-black bg-gradient-to-b from-[#0381FF] to-[#02067A]"
            >
                {/* manage printer */}
                <div className="bg-white p-6 rounded-lg shadow-lg max-w-5xl w-full mt-3 mb-3 justify-center items-center">
                    <h1 className="text-center text-4xl">THAY ĐỔI CHÍNH SÁCH</h1>
                    <hr className="h-1 mx-auto bg-black border-0 rounded md:my-5 dark:bg-gray-700 mb-5" />
                    <form className="space-y-12">
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="pagenum">Số trang mặc định</Label>
                            <Input
                                className="w-full"
                                required
                                value={pagenum}
                                onChange={(e) => setPageNum(Number(e.target.value))}
                                id="pagenum"
                                type="number"
                            />
                        </div>
                        <div className="grid w-full items-center gap-1.5">
                            <Label htmlFor="date">Ngày cung cấp hàng tháng</Label>
                            <Input
                                className="w-full"
                                required
                                value={day}
                                onChange={(e) => setDay(Number(e.target.value))}
                                id="date"
                                type="number"
                            />
                        </div>
                        <div className="flex w-full items-center gap-1.5">
                            <Label htmlFor="date" className="w-auto text-nowrap">Loại tập tin</Label>
                            <Input
                                className="w-3/4"
                                value={currentTopic}
                                onChange={(e) => setCurrentTopic(e.target.value)}
                                id="topics"
                                type="text"
                            />
                            <button
                                type="button"
                                onClick={handleAddTopic}
                                className='w-1/4 rounded-lg text-white px-1 py-2 ml-8 bg-hcmut-light hover:bg-hcmutLightBlue cursor-pointer'
                            >
                                Thêm loại
                            </button>
                        </div>
                        <div className="mt-5">
                            {topics.map((topic, index) => (
                                <span
                                    key={index}
                                    className="inline-block p-[5px_10px] m-[5px] rounded-[5px] cursor-pointer bg-green-400"
                                    onClick={() => handleRemoveTopic(topic)}
                                >
                                    {topic} &times;
                                </span>
                            ))}
                        </div>
                        {error && <Alert>{error}</Alert>}
                        <div className="w-full">
                            <button type="button" className="w-full bg-hcmut-light text-white rounded-md p-2 hover:bg-red-600" onClick={async () => {
                                const response = await addConfig({
                                    defaultNumberOfPages: pagenum,
                                    paperSupplyDay: day,
                                    createdBy: "aaaa",
                                    fileTypes: topics
                                });

                                if (response !== 200) {
                                    setError('Có lỗi xảy ra! vui lòng thử lại.');
                                    return;
                                }

                                const newConfig = await getConfig();
                                if (newConfig) {
                                    setPageNum(newConfig.defaultNumberOfPages);
                                    setDay(newConfig.paperSupplyDay);
                                    setTopics(newConfig.fileTypes);
                                } else {
                                    alert('Có lỗi xảy ra! Vui lòng thử lại...')
                                }
                            }}>
                                Lưu
                            </button>
                            <button onClick={() => redirect("/spso")} type="button" className="w-full mt-5 bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
                                Hoàn thành
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}