async function fetchCocktailsWithIngredients() {
    const [cocktails] = await connection.query(`
      SELECT c.cocktail_id, c.name, ci.ingredient_id, ci.quantity as required_quantity
      FROM cocktails c
      JOIN cocktail_ingredients ci ON c.cocktail_id = ci.cocktail_id
    `);
    return cocktails;
  }
  
  async function fetchCurrentInventory() {
    const [inventory] = await connection.query(`
      SELECT ingredient_id, quantity
      FROM ingredient_inventory
      WHERE status = 'available'
    `);
    return inventory;
  }
  
  async function filterAvailableCocktails() {
    const cocktails = await fetchCocktailsWithIngredients();
    const inventory = await fetchCurrentInventory();
    
    // Convert inventory to a map for easier lookup
    const inventoryMap = new Map(inventory.map(item => [item.ingredient_id, item.quantity]));
  
    const availableCocktails = cocktails.reduce((acc, cocktail) => {
      const { cocktail_id, name, ingredient_id, required_quantity } = cocktail;
  
      if (!acc[cocktail_id]) {
        acc[cocktail_id] = { name, canMake: true };
      }
  
      const availableQuantity = inventoryMap.get(ingredient_id) || 0;
      if (required_quantity > availableQuantity) {
        acc[cocktail_id].canMake = false;
      }
  
      return acc;
    }, {});
  
    return Object.entries(availableCocktails)
                 .filter(([_, { canMake }]) => canMake)
                 .map(([id, { name }]) => ({ id, name }));
  }
  
  filterAvailableCocktails().then(availableCocktails => {
    console.log("Available Cocktails:", availableCocktails);
  }).catch(err => {
    console.error("Error:", err);
  });
  async function updateInventoryQuantity(inventoryId, newQuantity) {
    const status = newQuantity > 0 ? 'available' : 'out of stock';
    await connection.query(`
      UPDATE ingredient_inventory
      SET quantity = ?, status = ?
      WHERE inventory_id = ?
    `, [newQuantity, status, inventoryId]);
  }
    