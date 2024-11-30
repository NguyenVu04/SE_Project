import Link from 'next/link';
import React from 'react';
import { FaPrint } from 'react-icons/fa'

const MainFooter = () => {
  return (
    <div

      className="flex justify-center items-center px-5 h-48 bg-[#d9d9d9] absolute bottom-0 left-0 right-0" // Sử dụng justify-center để căn giữa
    >
      <Link href="/student/upload">
        <button
          type='button'
          className="bg-[#6000D5] text-white py-4 px-8 rounded-lg flex flex-col items-center justify-center border-2 border-black hover:bg-blue-500">
          <FaPrint className="text-white mb-1 text-5xl" /> {/* Thay đổi vị trí biểu tượng máy in */}
          <span className="text-lg mt-5">IN TÀI LIỆU</span>
        </button>
      </Link>
    </div>
  );
};

export default MainFooter;
