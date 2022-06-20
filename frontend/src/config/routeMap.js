import Loadable from 'react-loadable';
import Loading from 'E:/reactmrp/frontend/src/components/Loading'
const Dashboard = Loadable({loader: () => import(/*webpackChunkName:'Dashboard'*/'E:/reactmrp/frontend/src/views/dashboard'),loading: Loading});
const Doc = Loadable({loader: () => import(/*webpackChunkName:'Doc'*/'E:/reactmrp/frontend/src/views/doc'),loading: Loading});
const Guide = Loadable({loader: () => import(/*webpackChunkName:'Guide'*/'E:/reactmrp/frontend/src/views/guide'),loading: Loading});
const Explanation = Loadable({loader: () => import(/*webpackChunkName:'Explanation'*/'E:/reactmrp/frontend/src/views/permission'),loading: Loading});
const AdminPage = Loadable({loader: () => import(/*webpackChunkName:'AdminPage'*/'E:/reactmrp/frontend/src/views/permission/adminPage'),loading: Loading});
const GuestPage = Loadable({loader: () => import(/*webpackChunkName:'GuestPage'*/'E:/reactmrp/frontend/src/views/permission/guestPage'),loading: Loading});
const EditorPage = Loadable({loader: () => import(/*webpackChunkName:'EditorPage'*/'E:/reactmrp/frontend/src/views/permission/editorPage'),loading: Loading});
const RichTextEditor = Loadable({loader: () => import(/*webpackChunkName:'RichTextEditor'*/'E:/reactmrp/frontend/src/views/components-demo/richTextEditor'),loading: Loading});
const Markdown = Loadable({loader: () => import(/*webpackChunkName:'Markdown'*/'E:/reactmrp/frontend/src/views/components-demo/Markdown'),loading: Loading});
const Draggable = Loadable({loader: () => import(/*webpackChunkName:'Draggable'*/'E:/reactmrp/frontend/src/views/components-demo/draggable'),loading: Loading});
const KeyboardChart = Loadable({loader: () => import(/*webpackChunkName:'KeyboardChart'*/'E:/reactmrp/frontend/src/views/charts/keyboard'),loading: Loading});
const LineChart = Loadable({loader: () => import(/*webpackChunkName:'LineChart'*/'E:/reactmrp/frontend/src/views/charts/line'),loading: Loading});
const MixChart = Loadable({loader: () => import(/*webpackChunkName:'MixChart'*/'E:/reactmrp/frontend/src/views/charts/mixChart'),loading: Loading});
const Menu1_1 = Loadable({loader: () => import(/*webpackChunkName:'Menu1_1'*/'E:/reactmrp/frontend/src/views/nested/menu1/menu1-1'),loading: Loading});
const Menu1_2_1 = Loadable({loader: () => import(/*webpackChunkName:'Menu1_2_1'*/'E:/reactmrp/frontend/src/views/nested/menu1/menu1-2/menu1-2-1'),loading: Loading});
const Table = Loadable({loader: () => import(/*webpackChunkName:'Table'*/'E:/reactmrp/frontend/src/views/table'),loading: Loading});
const ExportExcel = Loadable({loader: () => import(/*webpackChunkName:'ExportExcel'*/'E:/reactmrp/frontend/src/views/excel/exportExcel'),loading: Loading});
const UploadExcel = Loadable({ loader: () => import(/*webpackChunkName:'UploadExcel'*/'E:/reactmrp/frontend/src/views/excel/uploadExcel'),loading: Loading });
const Zip = Loadable({loader: () => import(/*webpackChunkName:'Zip'*/'E:/reactmrp/frontend/src/views/zip'),loading: Loading});
const Clipboard = Loadable({loader: () => import(/*webpackChunkName:'Clipboard'*/'E:/reactmrp/frontend/src/views/clipboard'),loading: Loading});
const Error404 = Loadable({loader: () => import(/*webpackChunkName:'Error404'*/'E:/reactmrp/frontend/src/views/error/404'),loading: Loading});
const User = Loadable({loader: () => import(/*webpackChunkName:'User'*/'E:/reactmrp/frontend/src/views/user'),loading: Loading});
const About = Loadable({loader: () => import(/*webpackChunkName:'About'*/'E:/reactmrp/frontend/src/views/about'),loading: Loading});
const Bug = Loadable({loader: () => import(/*webpackChunkName:'Bug'*/'E:/reactmrp/frontend/src/views/bug'),loading: Loading});

export default [
  { path: "/dashboard", component: Dashboard, roles: ["admin","editor","guest"] },
  { path: "/doc", component: Doc, roles: ["admin","editor","guest"] },
  { path: "/guide", component: Guide, roles: ["admin","editor"] },
  { path: "/permission/explanation", component: Explanation, roles: ["admin"] },
  { path: "/permission/adminPage", component: AdminPage, roles: ["admin"] },
  { path: "/permission/guestPage", component: GuestPage, roles: ["guest"] },
  { path: "/permission/editorPage", component: EditorPage, roles: ["editor"] },
  { path: "/components/richTextEditor", component: RichTextEditor, roles: ["admin","editor"] },
  { path: "/components/Markdown", component: Markdown, roles: ["admin","editor"] },
  { path: "/components/draggable", component: Draggable, roles: ["admin","editor"] },
  { path: "/charts/keyboard", component: KeyboardChart, roles: ["admin","editor"] },
  { path: "/charts/line", component: LineChart, roles: ["admin","editor"] },
  { path: "/charts/mix-chart", component: MixChart, roles: ["admin","editor"] },
  { path: "/nested/menu1/menu1-1", component: Menu1_1, roles: ["admin","editor"] },
  { path: "/nested/menu1/menu1-2/menu1-2-1", component: Menu1_2_1, roles: ["admin","editor"] },
  { path: "/table", component: Table, roles: ["admin","editor"] },
  { path: "/excel/export", component: ExportExcel, roles: ["admin","editor"] },
  { path: "/excel/upload", component: UploadExcel, roles: ["admin","editor"] },
  { path: "/zip", component: Zip, roles: ["admin","editor"] },
  { path: "/clipboard", component: Clipboard, roles: ["admin","editor"] },
  { path: "/user", component: User, roles: ["admin"] },
  { path: "/about", component: About, roles: ["admin", "editor", "guest"] },
  { path: "/bug", component: Bug, roles: ["admin"] },
  { path: "/error/404", component: Error404 },
];
