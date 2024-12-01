import React from 'react';
import Image from 'next/image'
import { FaRegUserCircle } from "react-icons/fa";
import Link from 'next/link';
const MainHeader = ({balance}: {balance: number}) => {
  return (
    <div  
      className="bg-white flex justify-between items-center px-8 h-12 absolute top-0 left-0 right-0 z-10">
      <Image src="/hcmut.svg" alt="Mô tả ảnh" width={30} height={30} className='cursor-pointer'/>
      <div className="flex items-center">
        <div 
            className="rounded-full px-4 py-1 flex items-center mr-8 bg-[#08FF3D]">
            Số trang còn lại: {balance}
        </div>
        <Link href="/student">
        <FaRegUserCircle className='text-4xl'/>
        </Link>
        
      </div>
    </div>
  );
};

export default MainHeader;
