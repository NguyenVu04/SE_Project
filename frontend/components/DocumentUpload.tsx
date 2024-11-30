'use client'
import Link from 'next/link';
import React, { useEffect } from 'react';
import { MdUploadFile } from "react-icons/md";
import { IoDocumentOutline } from "react-icons/io5";
import addFile from '@/lib/add-file';

const studentId = "admin";
const DocumentUpload = () => {
  useEffect(() => {

  })

  return (
    <div
      className="absolute top-[70px] left-[150px] right-[150px] bottom-[30px] bg-white flex justify-between items-center py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <div 
          className='w-[480px] h-[600px] relative bg-blue-200 rounded-3xl border-2 border-dashed border-purple-500 flex flex-col items-center justify-center'>
            <MdUploadFile className="text-9xl mb-5" />
            <p className="text-center text-2xl">Kéo và Thả tập tin vào đây <br /> hoặc</p>
            <button 
              type='button'
              className="mt-4 bg-green-400 text-2xl px-10 py-4 rounded-full shadow-lg">
              Duyệt
            </button>
            <input 
            type="file" 
            className='w-full h-full absolute top-0 left-0 opacity-0' 
            name='file' 
            placeholder='Upload file' 
            onChange={async (e) => {
              const file = e.target.files?.[0];
              
              if (file) {
                const status = await addFile(studentId, file);
                if (status === 409) {
                  alert('File đã tồn tại...');
                } else if (status !== 200) {
                  alert("Có lỗi xảy ra! Vui lòng thử lại...");
                } else {
                  alert('Thành công!');
                }
                window.location.reload();
              }
            }} />
        </div>
        <div 
          className="flex flex-col justify-between rounded-2xl p-8 shadow-lg w-[540px] h-[600px] bg-[rgba(17,255,0,0.28)]">
          <div>
            <h2 className="text-2xl mb-2">Tài liệu đã tải lên</h2>
            <hr className="border-b-2 border-black w-full mb-5" />

            <div className="flex flex-col space-y-6 w-full">

              <div className="bg-white flex items-center border-2 border-black p-2 rounded-lg">
                <IoDocumentOutline className='text-3xl'/>
                <span className="ml-2">myfile.pdf</span>
              </div>

            </div>
          </div>
          <Link href="/student/document">
            <button type='button' className="ml-80 bg-gray-800 text-white p-4 rounded-full shadow-md">
              Hoàn thành
            </button>
          </Link>
        </div>
    </div>
  )
}

export default DocumentUpload