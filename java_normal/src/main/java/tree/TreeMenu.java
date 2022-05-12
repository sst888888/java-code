package tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * tree树
 * @author admin
 *
 */
public class TreeMenu implements Serializable{

	private static final long serialVersionUID = 2080504332055928137L;
	
	private Long id;
	private Long parentId;
	private String name;
	private String url;
	private Boolean checked;//是否被选中
	private Integer category;//后台为 quotaCategory
	private String owner;
	private String code;
	private List<TreeMenu> children;

	public TreeMenu() {
		super();
	}

	public TreeMenu(Long id, Long parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
	
	public TreeMenu(Long id, Long parentId, String name, Boolean checked) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.checked = checked;
	}

	public TreeMenu(Long id, Long parentId, String code, String name, Boolean checked) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.checked = checked;
	}

	public TreeMenu(Long id, Long parentId, String name, Boolean checked, String url, String owner) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.checked = checked;
		this.url = url;
		this.owner = owner;
	}

	public TreeMenu(Long id, Long parentId, String name, Boolean checked, String url) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
	}
	
	/**
	 * 生成树
	 * @param list
	 * @param parentId
	 * @return
	 */
	public static List<TreeMenu> buildTree(List<TreeMenu> list,Long parentId){
		List<TreeMenu> trees = new ArrayList<TreeMenu>();
		
		for (TreeMenu tree : list) {  
			Long id = tree.getId();
    		Long pid = tree.getParentId();
    		
	        if(parentId.longValue() == pid.longValue()) {
	        	List<TreeMenu> treeLists = buildTree(list, id);
	        	if(null != treeLists && treeLists.size() > 0) {
	        		tree.setChildren(treeLists);
	        	}
	        	trees.add(tree);  
	        }  
		}  
		      
		return trees;
	}
	
	/**
	 * 生成父级树
	 * @param list
	 * @param parentId
	 * @return
	 */
	public static List<TreeMenu> buildParentTree(List<TreeMenu> list,Long parentId){
		List<TreeMenu> trees = buildTree(list, parentId);
		return delTree(trees);
	}
	
	/**
	 * 生成选中的末级节点
	 * @param list 一颗树
	 * @return
	 */
	public static List<Long> buildChildNode(List<TreeMenu> list){
		List<Long> childrenList = new ArrayList<Long>();
		return childrenNode(list,childrenList);
	}
	
	public static List<TreeMenu> delTree(List<TreeMenu> list){
		for (int i = 0;i < list.size();i++) {
			TreeMenu treeMenu = list.get(i); 
			List<TreeMenu> childrenList = treeMenu.getChildren();
			if(null == childrenList) {
				list.removeAll(list);
			}else {
				delTree(childrenList);
			}
		}
		return list;
	}
	
	public static List<Long> childrenNode(List<TreeMenu> list,List<Long> childrenList){
		for (int i = 0;i < list.size();i++) {
			TreeMenu treeMenu = list.get(i); 
			List<TreeMenu> children = treeMenu.getChildren();
			Boolean checked = treeMenu.getChecked();
			if(null == children) {
				if(null != checked && checked) {
					childrenList.add(treeMenu.getId());
				}
			}else {
				childrenNode(children,childrenList);
			}
		}
		return childrenList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TreeMenu> children) {
		this.children = children;
	}
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static List<TreeMenu> ffff(List<TreeMenu> list, List<TreeMenu> trees , Long parentId){
		for (TreeMenu tree : list) {
			Long id = tree.getId();
			Long pid = tree.getParentId();

			if(parentId.longValue() == pid.longValue()) {
				trees.add(tree);
				ffff(list,trees, id);
			}

		}

		return trees;
	}

	public static void main(String[] args) {
		List<TreeMenu> list = new ArrayList<TreeMenu>();
		TreeMenu t1 = new TreeMenu(1l, 0l, "a1",null);
		TreeMenu t2 = new TreeMenu(2l, 0l, "b1",null);
		TreeMenu t3 = new TreeMenu(3l, 1l, "a2",null);
		TreeMenu t4 = new TreeMenu(4l, 1l, "a3",null);
		TreeMenu t5 = new TreeMenu(5l, 3l, "a4",true);
		TreeMenu t6 = new TreeMenu(6l, 3l, "a5",null);
		TreeMenu t7 = new TreeMenu(7l, 4l, "a6",null);
		TreeMenu t8 = new TreeMenu(8l, 4l, "a7",null);
		TreeMenu t9 = new TreeMenu(9l, 2l, "b2",null);
		TreeMenu t10 = new TreeMenu(10l, 9l, "b3",true);
		
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		list.add(t7);
		list.add(t8);
		list.add(t9);
		list.add(t10);

		List<TreeMenu> list1 = new ArrayList<>();
		List<TreeMenu> result = ffff(list,list1, 1L);
		System.out.println(result);
//		String trssJson=JSON.toJSONString(result);
//		System.out.println(trssJson);
		
	}
}
