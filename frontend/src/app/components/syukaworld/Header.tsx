"use client";
import React, { useState } from "react";
export function SyukaWorldHeader() {
  const [activeMenu, setActiveMenu] = useState<number | null>(null);
  const menuItems = [
    {
      title: "인기영상",
      subItems: ["서브메뉴 1-1", "서브메뉴 1-2", "서브메뉴 1-3"],
    },
    {
      title: "토론방",
      subItems: ["서브메뉴 2-1", "서브메뉴 2-2", "서브메뉴 2-3"],
    },
    {
      title: "주제추천",
      subItems: ["서브메뉴 3-1", "서브메뉴 3-2", "서브메뉴 3-3"],
    },
    {
      title: "자유게시판",
      subItems: ["서브메뉴 3-1", "서브메뉴 3-2", "서브메뉴 3-3"],
    },
    {
      title: "피드백",
      subItems: ["서브메뉴 3-1", "서브메뉴 3-2", "서브메뉴 3-3"],
    },
  ];
  return (
    <div className="relative">
      <header className="w-full border-b bg-white">
        <div className="max-w-8xl px-[4.375vw] h-20 flex items-center justify-between">
          <div className="flex items-center gap-8">
            <div className="flex items-center gap-4">
              <img
                src="/images/newlogo.png"
                alt="SyukaWorld"
                className="h-14"
              />
              <span className="text-2xl font-bold text-black">슈카월드</span>
            </div>
            <nav className="flex items-center ml-40">
              <ul className="flex gap-10 text-black text-xl font-semibold">
                {menuItems.map((item, index) => (
                  <li
                    key={index}
                    onMouseEnter={() => setActiveMenu(index)}
                    onMouseLeave={() => setActiveMenu(null)}
                    className="relative py-5"
                  >
                    <a href="#" className="hover:text-gray-600">
                      {item.title}
                    </a>
                  </li>
                ))}
              </ul>
            </nav>
          </div>
          <div className="flex items-right gap-5 text-gray-500 mr-[1.1vw]">
            <button className="flex items-center gap-1 hover:text-gray-300">
              <span>로그인</span>
            </button>
            <button className="flex items-center gap-1 hover:text-gray-300">
              <span>회원가입</span>
            </button>
            <button className="flex items-center gap-1 hover:text-gray-300">
              <span>슈카코믹스로 가기</span>
            </button>
          </div>
        </div>
      </header>
      {/* Dropdown Menu */}
      {activeMenu !== null && (
        <div className="absolute w-full top-full">
          <div
            className="absolute w-full h-4 -top-4"
            onMouseEnter={() => setActiveMenu(activeMenu)}
          />

          <div
            className="absolute w-full h-[15vh] bg-gray-100 shadow-lg transition-all duration-300 ease-in-out"
            onMouseEnter={() => setActiveMenu(activeMenu)}
            onMouseLeave={() => setActiveMenu(null)}
          >
            <div className="max-w-7xl mx-auto px-4 py-6">
              <ul className="flex flex-row gap-10 text-xl text-black">
                {menuItems[activeMenu].subItems.map((subItem, index) => (
                  <li key={index}>
                    <a href="#" className="hover:text-gray-600">
                      {subItem}
                    </a>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default SyukaWorldHeader;
