import React from 'react';
import Image from 'next/image';
import { FaRegUserCircle } from "react-icons/fa";
const TopBar = () => {
  return (
    <div className="flex items-center justify-between px-4 py-2 mb-2">
        <Image src="/640px-HCMUT_official_logo.png" alt="logo-hcmut" width={50} height={50} />
        <FaRegUserCircle className="text-4xl text-hcmut-dark"/>
    </div>
  );
};

export default TopBar;
