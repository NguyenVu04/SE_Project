import Link from 'next/link';
import React from 'react';
import { FaBars } from 'react-icons/fa';
import Image from 'next/image'

const MainHeader = () => {
  return (
    <div  
      style={{ position: 'absolute', top: 0, left: 0, right: 0, zIndex: 1 }}
      className="bg-white flex justify-between items-center px-8 h-12">
      <Image src="/hcmut.svg" alt="Mô tả ảnh" width={30} height={30} className='cursor-pointer'/>
      <div className="flex items-center">
        <div 
            style={{ backgroundColor: '#08FF3D' }} 
            className="rounded-full px-4 py-1 flex items-center mr-8">
            Số trang còn lại: 10
        </div>
        <Image src="/hcmut.svg" alt="Mô tả ảnh" width={30} height={30} className='cursor-pointer'/>
      </div>
    </div>
  );
};

export default MainHeader;
