import Link from 'next/link';
import React from 'react';
import { MdUploadFile } from "react-icons/md";
import { IoDocumentOutline } from "react-icons/io5";

const DocumentUpload = () => {
  return (
    <div
      
      style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
      className="bg-white flex justify-between items-center py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <div 
          style={{ width: '480px', height: '600px' }}
          className='bg-blue-200 rounded-3xl border-2 border-dashed border-purple-500 flex flex-col items-center justify-center'>
            <MdUploadFile className="text-9xl mb-5" />
            <p className="text-center text-2xl">Kéo và Thả tập tin vào đây <br /> hoặc</p>
            <button 
              style={{
                background: '#00FF09'
              }}
              className="mt-4 bg-green-400 text-2xl px-10 py-4 rounded-full shadow-lg">
              Duyệt
            </button>
        </div>
        <div 
          className="flex flex-col justify-between rounded-2xl p-8 shadow-lg" 
          style={{ width: '540px', height: '600px', backgroundColor: 'rgba(17, 255, 0, 0.28)' }}>
          <div>
            <h2 className="text-2xl mb-2">Tài liệu đã tải lên</h2>
            <hr className="border-b-2 border-black w-full mb-5" />
            <div className="flex flex-col space-y-6 w-full">
              <div className="bg-white flex items-center border-2 border-black p-2 rounded-lg">
                <IoDocumentOutline className='text-3xl'/>
                <span className="ml-2">myfile.pdf</span>
              </div>

              <div className="bg-white flex items-center border-2 border-black p-2 rounded-lg">
                <IoDocumentOutline className='text-3xl'/>
                <span className="ml-2">myfile.pdf</span>
              </div>

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