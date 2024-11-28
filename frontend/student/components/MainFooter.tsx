import Link from 'next/link';
import React from 'react';
import { FaPrint } from 'react-icons/fa'

const MainFooter = () => {
  return (
    <div
      
      style={{ backgroundColor: '#d9d9d9', position: 'absolute', bottom: 0, left: 0, right: 0 }}
      className="flex justify-center items-center px-5 h-48" // Sử dụng justify-center để căn giữa
    >
      <Link href="/upload">
        <button 
          style={{ backgroundColor: '#6000D5' }}
          className="text-white py-4 px-8 rounded-lg flex flex-col items-center justify-center border-2 border-black">
          <FaPrint className="text-white mb-1 text-5xl" /> {/* Thay đổi vị trí biểu tượng máy in */}
          <span className="text-lg mt-5">IN TÀI LIỆU</span>
        </button>
      </Link>
    </div>
  );
};

export default MainFooter;
