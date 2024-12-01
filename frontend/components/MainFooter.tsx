import Link from 'next/link';
import React from 'react';
import { IoPrintOutline } from "react-icons/io5";
import { IoReceiptOutline } from "react-icons/io5";
const MainFooter = () => {
  return (
    <div

      className="flex justify-evenly items-center px-5 h-48 bg-[#d9d9d9] absolute bottom-0 left-0 right-0" // Sử dụng justify-center để căn giữa
    >
      <Link href="/student/upload">
        <button
          type='button'
          className="bg-[#6000D5] text-white py-4 px-8 rounded-lg flex flex-col items-center justify-center border-2 border-black hover:bg-blue-500">
          <IoPrintOutline className="text-white mb-1 text-5xl" /> {/* Thay đổi vị trí biểu tượng máy in */}
          <span className="text-lg mt-5">IN TÀI LIỆU</span>
        </button>
      </Link>
      <Link href="/student/order">
        <button
          type='button'
          className="bg-[#6000D5] text-white py-4 px-8 rounded-lg flex flex-col items-center justify-center border-2 border-black hover:bg-blue-500">
          <IoReceiptOutline className="text-white mb-1 text-5xl" /> {/* Thay đổi vị trí biểu tượng máy in */}
          <span className="text-lg mt-5">XEM HÓA ĐƠN</span>
        </button>
      </Link>
    </div>
  );
};

export default MainFooter;
