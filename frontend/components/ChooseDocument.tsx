'use client'
import React, { useEffect, useState } from 'react'
import Link from 'next/link';
import { IoDocumentOutline } from "react-icons/io5";
import { FileInfo } from './DocumentUpload';
import getDocuments from '@/lib/get-documents';
const studentId = "admin";
const ChooseDocument = () => {
    const [uploadedFiles, setUploadedFiles] = useState<FileInfo[]>([]);
    useEffect(() => {
        getDocuments(studentId)
            .then((res) => setUploadedFiles(res))
            .catch(err => alert(err));
    }, [])

    return (
        <div
            className="absolute top-[70px] left-[150px] right-[150px] bottom-[30px] bg-white flex flex-col items-center justify-between py-5 px-20" // Sử dụng justify-center để căn giữa
        >
            <div className='flex flex-col items-center w-full'>
                <h2 className="text-3xl mt-2">CHỌN TÀI LIỆU CẦN IN</h2>
                <hr className="border-b border-black w-full mb-10 mt-2" />
                <div className="flex flex-col space-y-10 w-full">
                    <div className="flex flex-row flex-wrap justify-around w-full px-1">
                        {
                            uploadedFiles.map((file) => (
                                <Link key={file.fileName} href={`/student/configdoc?fileName=${file.fileName}`} className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-2/5">
                                    <IoDocumentOutline className='text-3xl' />
                                    <span className="ml-2">{file.fileName}</span>
                                </Link>
                            ))
                        }
                    </div>
                </div>
            </div>
            <div className="flex justify-end space-x-6 mt-8 w-full"> {/* Bố cục nút bên phải */}
                <Link href="/student/payment">
                    <button
                        type='button'
                        className="p-4 rounded-lg border-black border-2 bg-[#08FF3D] w-[150px]"
                    >
                        Mua thêm trang
                    </button>
                </Link>

                <Link href="/student/upload">
                    <button
                        type='button'
                        className="text-white p-4 rounded-lg border-black border-2 bg-[#0064F0] w-[150px]"
                    >
                        Tải lên tài liệu
                    </button>
                </Link>
            </div>
        </div>
    )
}

export default ChooseDocument