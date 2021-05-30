import { Grid, makeStyles, FormControl, MenuItem, Select, InputLabel, Typography  } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';
import Pagination from '@material-ui/lab/Pagination';
import * as Scroll from 'react-scroll';
import { CenterFocusStrong } from '@material-ui/icons';

let scroll = Scroll.animateScroll;

const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: 120,
    justifyContent: 'flex-end',
  },
  sortControl: {
    margin: theme.spacing(1),
    minWidth: 300,
  }
}));

function RamPicker() {
  const classes = useStyles();
  const [totalPages, setTotalPages] = useState(0);
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1);
  
  const fetchProducts = (p, t, s, o) => {
    axios.get("http://localhost:8080/products/ram?page=" + (p - 1) + "&size=" + t + "&sortBy=" + s + "&sortingOrder=" + o).then(res => {
      console.log(res.data.products);
      setProducts(res.data.products);
      setTotalPages(res.data.totalPages);
    });
  };

  useEffect(() => {
    fetchProducts(1, 30, '', '');
  }, []);
  

  const handleChangePage = (event, value) => {
    setPage(value);
    fetchProducts(value,totalItems,sortBy,sortOrder);
    scroll.scrollToTop();
  };

  const [sortBy, setSortBy] = useState('');
  const [sortSelect, setSortSelect] = useState('');
  const [sortOrder, setSortOrder] = useState('');
  const [totalItems, setTotalItems] = useState(30);
  const [open, setOpen] = useState(false);
  const [openSort, setOpenSort] = useState(false);

  const handleTotalItemsChange = (event) => {
    setTotalItems(event.target.value);
    fetchProducts(page,event.target.value,sortBy,sortOrder);
  };
  
  const handleSortChange = (event) => {
    setSortSelect(event.target.value);
    switch(event.target.value) {
      case "brand_asc":
        setSortBy('product.brand');
        setSortOrder('asc');
        fetchProducts(page,totalItems,'product.brand','asc');

        break;
      case "brand_desc":
        setSortBy('product.brand');
        setSortOrder('desc');
        fetchProducts(page,totalItems,'product.brand','desc');

        break;
      case "price_asc":
        setSortBy('product.price');
        setSortOrder('asc');
        fetchProducts(page,totalItems,'product.price','asc');
        break;
      case "price_desc":
        setSortBy('product.brand');
        setSortOrder('desc');
        fetchProducts(page,totalItems,'product.price','desc');
        break;
    }
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
      <Grid container spacing={3}>
        <Grid item xs={12}>
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
              <MenuItem value={'brand_desc'}>Marka malejąco</MenuItem>
              <MenuItem value={'brand_asc'}>Marka rosnąco</MenuItem>
              <MenuItem value={'price_desc'}>Cena malejąco</MenuItem>
              <MenuItem value={'price_asc'}>Cena rosnąco</MenuItem>
            </Select> 
          </FormControl>
        </Grid>
      </Grid>
      <Grid container alignItems="center" spacing={3}>
        {products.map(({product, moduleCapacity, speed, modulesCount, cl}, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name + " (" + modulesCount + " x " + moduleCapacity + " GB)" }
              price={Number(product.price).toFixed(2)}
              detail0={"Taktowanie  : " + speed + " MHz" }
              detail1={"Opóżnienie: CL" + cl }
              detail2={"Pojemność całkowita: " + (moduleCapacity * modulesCount) + " GB" }
            />
          </Grid>
        ))}
        </Grid>
        <Grid container spacing={3}>
          <Grid item xs={4}>
            <Typography align="center|right">
            <FormControl variant="filled" className={classes.formControl}>
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
            </Typography>
          </Grid>
      </Grid>
      <Pagination count={totalPages} page={page} onChange={handleChangePage} />
      </div>
  );
}

export default RamPicker
