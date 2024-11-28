import React from 'react';
import MainHeader from './MainHeader';
import MainFooter from './MainFooter';

const MainLayout = () => {
  return (
      <div className="flex flex-col flex-grow p-4 items-center justify-center">
        <h1 
          style={{
            color: '#FF7700'
          }}
          className="text-6xl mb-4 mt-12">
            WELCOME TO SPSS
        </h1>
        <p className='text-white'>
          where quality printing meets exceptional service – bringing your ideas to life, one print at a time!
        </p>
      </div>

  );
};

export default MainLayout;
