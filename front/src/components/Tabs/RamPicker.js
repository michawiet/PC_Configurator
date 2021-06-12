import { Grid, makeStyles, FormControl, MenuItem, Select, InputLabel } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';
import Pagination from '@material-ui/lab/Pagination';
import * as Scroll from 'react-scroll';

let scroll = Scroll.animateScroll;

const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: 120,
    justifyContent: 'flex-end',
  },
  sortControl: {
    minWidth: 300,
  }
}));

function RamPicker() {
  const classes = useStyles();
  const [products, setProducts] = useState([]);

  const [totalPages, setTotalPages] = useState(0);
  const [totalItems, setTotalItems] = useState(30);
  const [currentPage, setCurrentPage] = useState(1);
  const [sortBy, setSortBy] = useState('');
  const [sortSelect, setSortSelect] = useState('');
  const [sortOrder, setSortOrder] = useState('');
  const [open, setOpen] = useState(false);
  const [openSort, setOpenSort] = useState(false);

  useEffect(() => {
    axios.get("http://localhost:8080/products/ram?page="
      + (currentPage - 1)
      + "&size="
      + totalItems
      + "&sortBy=" 
      + sortBy
      + "&sortingOrder="
      + sortOrder
    ).then(res => {
      setProducts(res.data.products);
      setTotalPages(res.data.totalPages);
    });
  }, [sortBy, sortSelect, sortOrder, totalItems, currentPage]);

  const handleChangePage = (event, value) => {
    setCurrentPage(value);
    scroll.scrollToTop();
  };

  const handleTotalItemsChange = (event) => {
    setTotalItems(event.target.value);
    setCurrentPage(1);
    scroll.scrollToTop();
  };
  
  const handleSortChange = (event) => {
    setSortSelect(event.target.value);
    switch(event.target.value) {
      case "brand_asc":
        setSortBy('product.brand');
        setSortOrder('asc');
        break;
      case "brand_desc":
        setSortBy('product.brand');
        setSortOrder('desc');
        break;
      case "price_asc":
        setSortBy('product.price');
        setSortOrder('asc');
        break;
      case "price_desc":
        setSortBy('product.price');
        setSortOrder('desc');
        break;
      default:
        break;
    }
    setCurrentPage(1);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };
  
  const handleCloseSort = () => {
    setOpenSort(false);
  };

  const handleOpenSort = () => {
    setOpenSort(true);
  };
  
  return (
    <div>
      <Grid container direction="row" justify="flex-end" alignItems="center" spacing={3}>
        <Grid item>
          <FormControl variant="outlined" className={classes.sortControl}>
          <InputLabel id="sortSelectLabel">Sortuj po</InputLabel>
            <Select
              id="sortSelect"
              open={openSort}
              onClose={handleCloseSort}
              onOpen={handleOpenSort}
              value={sortSelect}
              onChange={handleSortChange}
              label="Sortuj po"
            >
              <MenuItem value={'brand_desc'}>Marka Z-A</MenuItem>
              <MenuItem value={'brand_asc'}>Marka A-Z</MenuItem>
              <MenuItem value={'price_desc'}>Cena malejąco</MenuItem>
              <MenuItem value={'price_asc'}>Cena rosnąco</MenuItem>
            </Select> 
          </FormControl>
        </Grid>
      </Grid>
      <Grid container alignItems="center" spacing={3}>
        {products.map(({product, moduleCapacity, speed, modulesCount, cl}, index) => (
          <Grid item key={index} xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name }
              image={product.image}
              price={Number(product.price).toFixed(2)}
              detail0={ "Taktowanie: " + speed + " MHz" }
              detail1={ "Opóżnienie: CL" + cl }
              detail2={ "Pojemność całkowita: " + (moduleCapacity * modulesCount) + " GB" }
              detail3={ "Ilość modułów: " + modulesCount }
              productID={product.id}
            />
          </Grid>
        ))}
        </Grid>
        <Grid container direction="row" justify="space-between" alignItems="center" spacing={5}>
          <Grid item>
            <FormControl variant="outlined" className={classes.formControl}>
              <InputLabel id="totalPagesLabel">liczba wyników</InputLabel>
              <Select
                id="totalPagesSelect"
                open={open}
                onClose={handleClose}
                onOpen={handleOpen}
                value={totalItems}
                onChange={handleTotalItemsChange}
                label="liczba wyników"
              >
                <MenuItem value={30}>30</MenuItem>
                <MenuItem value={60}>60</MenuItem>
                <MenuItem value={90}>90</MenuItem>
              </Select> 
            </FormControl>
          </Grid>
          <Grid item>
          <Pagination variant="outlined" color="primary" count={totalPages} page={currentPage} onChange={handleChangePage} />
          </Grid>
      </Grid>
    </div>
  );
}

export default RamPicker
