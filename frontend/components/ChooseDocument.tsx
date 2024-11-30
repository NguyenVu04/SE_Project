import React from 'react'
import Link from 'next/link';
import { IoDocumentOutline } from "react-icons/io5";

const ChooseDocument = () => {
  return (
    <div
      style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
      className="bg-white flex flex-col items-center justify-between py-5 px-20" // Sử dụng justify-center để căn giữa
    >
        <div className='flex flex-col items-center w-full'>
            <h2 className="text-3xl mt-2">CHỌN TÀI LIỆU CẦN IN</h2>
            <hr className="border-b border-black w-full mb-10 mt-2" />
            <div className="flex flex-col space-y-10 w-full">
                <div className="flex flex-row space-x-10 w-full">
                    <Link href="/student/configdoc" className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-1/2">
                        <IoDocumentOutline className='text-3xl'/>
                        <span className="ml-2">myfile.pdf</span>
                    </Link>
                    <Link href="/configDoc" className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-1/2">
                        <IoDocumentOutline className='text-3xl'/>
                        <span className="ml-2">myfile.pdf</span>
                    </Link>
                </div>

                <div className="flex flex-row space-x-10 w-full">
                    <Link href="/configDoc" className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-1/2">
                        <IoDocumentOutline className='text-3xl'/>
                        <span className="ml-2">myfile.pdf</span>
                    </Link>
                    <Link href="/configDoc" className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-1/2">
                        <IoDocumentOutline className='text-3xl'/>
                        <span className="ml-2">myfile.pdf</span>
                    </Link>
                </div>

                <div className="flex flex-row space-x-10 w-full">
                    <Link href="/configDoc" className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-1/2">
                        <IoDocumentOutline className='text-3xl'/>
                        <span className="ml-2">myfile.pdf</span>
                    </Link>
                    <Link href="/configDoc" className="bg-white flex items-center border-2 border-black p-2 rounded-lg w-1/2">
                        <IoDocumentOutline className='text-3xl'/>
                        <span className="ml-2">myfile.pdf</span>
                    </Link>
                </div>
            </div>
        </div>
        <div className="flex justify-end space-x-6 mt-8 w-full"> {/* Bố cục nút bên phải */}
            <Link href="/student/payment">
                <button
                    type='button'
                    style = {{background: '#08FF3D', width: '150px'}}
                    className="p-4 rounded-lg border-black border-2"
                >
                    Mua thêm trang
                </button>
            </Link>

            <Link href="/student/upload">
                <button
                    type='button'
                    style = {{background: '#0064F0', width: '150px'}}
                    className="text-white p-4 rounded-lg border-black border-2"
                >
                    Tải lên tài liệu
                </button>
            </Link>
        </div>
    </div>
  )
}

export default ChooseDocument