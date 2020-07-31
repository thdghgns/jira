import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  mode: 'hash',
  base: process.env.BASE_URL,

  routes: [
    {
      path: '/',
      component: () => import('@/views/dashboard/Index'),
      children: [
        // Dashboard
        {
          name: 'Dashboard',
          path: '',
          component: () => import('@/views/dashboard/pages/Dashboard'),
        },
        // Pages
        {
          name: 'Projects',
          path: 'projects',
          component: () => import('@/views/dashboard/pages/Projects'),
        },
        {
          name: 'Issues',
          path: 'issues',
          component: () => import('@/views/dashboard/pages/Issues'),
        },
        {
          name: 'Components',
          path: 'components',
          component: () => import('@/views/dashboard/pages/Components'),
        },
        {
          name: 'Login',
          path: 'login',
          component: () => import('@/views/Login'),
        },
        {
          name: 'Register',
          path: 'register',
          component: () => import('@/views/Register'),
        },
      ],
    },
  ],
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/']
  const authRequired = !publicPages.includes(to.path)
  const loggedIn = localStorage.getItem('user')

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login')
  } else {
    next()
  }
})

export default router
