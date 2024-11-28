import MainFooter from '@/components/MainFooter'
import MainHeader from '@/components/MainHeader'
import MainLayout from '@/components/MainLayout'
import React from 'react'

const Homepage = () => {
  return (
      <div 
        style={{
              background: 'linear-gradient(to bottom, #0381FF, #02067A)'
            }}
        className=" relative min-h-screen flex flex-col">
        <MainLayout />
        <MainFooter />
      </div>
  )
}

export default Homepage