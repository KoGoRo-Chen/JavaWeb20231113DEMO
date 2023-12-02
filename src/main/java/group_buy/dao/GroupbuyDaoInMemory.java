package group_buy.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import static java.util.stream.Collectors.toList;

import group_buy.entity.Cart;
import group_buy.entity.CartItem;
import group_buy.entity.Product;
import group_buy.entity.User;

// In-Memory
public class GroupbuyDaoInMemory implements GroupBuyDao {
	
	// In-Memory List
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static List<Product> products = new CopyOnWriteArrayList<>();
	private static List<Cart> carts = new CopyOnWriteArrayList<>();
	private static List<CartItem> cartItems = new CopyOnWriteArrayList<>();
	//copyonwritearraylist適用於多執行緒的小型集合，用於大集合會有效率的問題
	
	@Override
	public List<User> findAllUsers() {
		return users;
	} //回傳所有使用者列表。

	@Override
	public void addUser(User user) {
		users.add(user);
	} //將一個使用者物件添加到使用者列表中。

	@Override
	public Boolean updateUserPasswordById(Integer userId, String newPassword) {
		/*
		Optional<User> userOpt = users.stream()
									  .filter(user -> user.getUserId().equals(userId))
									  .findAny();
		if(userOpt.isPresent()) {
			userOpt.get().setPassword(newPassword);
			return true;
		}
		return false;
		*/
		return users.stream()
				  .filter(user -> user.getUserId().equals(userId))
				  .peek(user -> user.setPassword(newPassword))
				  .findAny()
				  .isPresent();
	} //根據使用者 ID 更新使用者密碼。

	@Override
	public Optional<User> findUserByName(String username) {
		return users.stream().filter(user -> user.getUsername().equals(username)).findAny();
	} //根據使用者名稱查找使用者。

	@Override
	public Optional<User> findUserById(Integer userId) {
		return users.stream().filter(user -> user.getUserId().equals(userId)).findAny();
		//return users.stream().filter(user -> user.getUserId().intValue() == userId.intValue()).findAny();
	} //根據使用者 ID 查找使用者。

	@Override
	public List<Product> findAllProducts() {
		return products;
	} //回傳所有產品列表。

	@Override
	public Optional<Product> findProductById(Integer productId) {
		return products.stream().filter(product -> product.getProductId().equals(productId)).findAny();
	} //根據產品 ID 查找產品。

	@Override
	public void addProduct(Product product) {
		products.add(product);
	} //將一個產品物件添加到產品列表中。

	@Override
	public Boolean updateProductLaunchById(Integer productId, Boolean isLaunch) {
		return products.stream()
					   .filter(product -> product.getProductId().equals(productId))
					   .peek(product -> product.setIsLaunch(isLaunch))
					   .findAny()
					   .isPresent();
	} //根據產品 ID 更新產品是否上架。

	@Override
	public void addCart(Cart cart) {
		carts.add(cart);
	} //將一個購物車物件添加到購物車列表中。

	@Override
	public void addCartItem(CartItem cartItem) {
		cartItems.add(cartItem);
	} // 將一個購物車項目物件添加到購物車項目列表中。

	@Override
	public List<Cart> findAllCarts() {
		return carts;
	} // 回傳所有購物車列表。

	@Override
	public Optional<Cart> findCartById(Integer cartId) {
		return carts.stream().filter(cart -> cart.getCartId().equals(cartId)).findAny();
	} //根據購物車 ID 查找購物車。

	@Override
	public Optional<CartItem> findCartItemById(Integer itemId) {
		return cartItems.stream().filter(cartItem -> cartItem.getItemId().equals(itemId)).findAny();
	} //根據購物車項目 ID 查找購物車項目。

	@Override
	public List<Cart> findCartsByUserId(Integer userId) {
		return carts.stream()
					.filter(cart -> cart.getUserId().equals(userId))
					.collect(toList());
	} //根據使用者 ID 查找購物車列表。

	@Override
	public List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		return carts.stream()
					//.filter(cart -> cart.getUserId().equals(userId))
					//.filter(cart -> cart.getIsCheckout().equals(isCheckout))
					.filter(cart -> cart.getUserId().equals(userId) && cart.getIsCheckout().equals(isCheckout))
					.collect(toList());
	} //根據使用者 ID 和結帳狀態查找購物車列表。

	@Override
	public Optional<Cart> findNoneCheckoutCartByUserId(Integer userId) {
		return carts.stream()
					.filter(cart -> cart.getUserId().equals(userId))
					.filter(cart -> cart.getIsCheckout() == null || !cart.getIsCheckout())
					.findAny();
	} //查找尚未結帳的購物車。

	@Override
	public Boolean checkoutCartByUserId(Integer userId) {
		// 尋找該使用者的尚未結帳購物車, 才可以進行結帳
		Optional<Cart> cartOpt = findNoneCheckoutCartByUserId(userId);
		if(cartOpt.isPresent()) {
			cartOpt.get().setIsCheckout(true); // 結帳
			return true;
		}
		return false;
	} // 根據使用者 ID 結帳購物車。

	@Override
	public Boolean checkoutCartById(Integer cartId) {
		return carts.stream()
					.filter(cart -> cart.getCartId().equals(cartId))
					.peek(cart -> cart.setIsCheckout(true)) // 結帳
					.findAny()
					.isPresent();
	} //根據購物車 ID 結帳購物車。

	@Override
	public Boolean removeCartItemById(Integer itemId) {
		Optional<CartItem> cartItemOpt = cartItems.stream()
												  .filter(cartItem -> cartItem.getItemId().equals(itemId))
												  .findAny();
		if(cartItemOpt.isPresent()) {
			cartItems.remove(cartItemOpt.get()); // 移除物件
			return true;
		}
		return false;
	} //根據購物車項目 ID 移除購物車項目。

	@Override
	public Boolean updateCartItemQuantityById(Integer itemId, Integer quantity) {
		 Optional<CartItem> cartItemOpt = cartItems.stream()
                 .filter(cartItem -> cartItem.getItemId().equals(itemId))
                 .findAny();
		 if (cartItemOpt.isPresent()) {
			 CartItem cartItem = cartItemOpt.get();
			 cartItem.setQuantity(quantity);
			 return true;
			 }
		 return false;
	} //根據購物車項目 ID 更新購物車項目數量。
	
	 
	
}
