import React from 'react';
import { Navigate } from 'react-router-dom';
import DashboardLayout from './layouts/DashboardLayout';
import MainLayout from './layouts/MainLayout';
import DashboardPage from './pages/DashboardPage';
import PostingPage from './pages/PostingPage';
import NotFoundPage from './pages/NotFoundPage';
import LoginPage from './pages/LoginPage';
import MainPage from './pages/MainPage';

const routes = [
  {
    path: 'app',
    element: <DashboardLayout />,
    children: [
      { path: '/main', element: <MainPage />},
      { path: '/dashboard', element: <DashboardPage /> },
      { path: '/posting', element: <PostingPage /> },
      // { path: '/tasks/:task_id/:task_name', element: <MetricsPage /> },
      { path: '*', element: <Navigate to="/404" /> }
    ]
  },
  {
    path: '/',
    element: <MainLayout />,
    children: [
      { path: 'login', element: <LoginPage /> },
      // { path: 'register', element: <RegisterPage /> },
      { path: '404', element: <NotFoundPage /> },
      { path: '/', element: <Navigate to="/app/dashboard" /> },
      { path: '*', element: <Navigate to="/404" /> }
    ]
  }
];

export default routes;
