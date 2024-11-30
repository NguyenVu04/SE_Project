import React from 'react';
import Image from 'next/image';
import { FaRegUserCircle } from "react-icons/fa";
import { useRouter } from 'next/navigation'; 
import { usePathname } from 'next/navigation'

const TopBar = () => {
  const router = useRouter() // Initialize the router instance
  const currentPath = usePathname() // Get the current pathname
  // Function to navigate up one directory
  const navigateUpOneFolder = () => {
    const newPath = currentPath.split('/').slice(0, -1).join('/'); // Remove the last part of the path
    router.push(newPath); // Navigate to the new path
  };

  return (
    <div className="flex items-center justify-between px-4 py-2 mb-2 top-0">
      <Image src="/640px-HCMUT_official_logo.png" alt="logo-hcmut" width={50} height={50} />
      <FaRegUserCircle
        className="text-4xl text-hcmut-dark cursor-pointer"
        onClick={navigateUpOneFolder} // Trigger navigation on click
      />
    </div>
  );
};

export default TopBar;
