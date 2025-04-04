const { createApp, defineComponent } = Vue;

// 注册Element Plus图标
const icons = ['Odometer', 'User', 'Setting'];
icons.forEach(name => {
    const icon = ElementPlusIconsVue[name];
    app.component(name, icon);
});

createApp({
    delimiters: ['[[', ']]'],
    data() {
        return {
            isCollapse: false,
            menus: this.processMenus(__MENUS__),
            user: __USER__,
            currentPath: window.location.pathname
        }
    },
    methods: {
        toggleSidebar() {
            this.isCollapse = !this.isCollapse;
        },
        processMenus(menus) {
            return menus.map(menu => ({
                ...menu,
                children: menu.children ? this.processMenus(menu.children) : null,
                icon: this.formatIconName(menu.icon)
            }));
        },
        formatIconName(icon) {
            return icon ? icon.charAt(0).toUpperCase() + icon.slice(1) : '';
        }
    }
})
.use(ElementPlus)
.mount('#app');